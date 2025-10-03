package br.edu.infnet.thomaspereiraapi.model.service;

import br.edu.infnet.thomaspereiraapi.client.CieloZeroAuthFeignClient;
import br.edu.infnet.thomaspereiraapi.model.domain.*;
import br.edu.infnet.thomaspereiraapi.model.domain.dto.CreditCardDTO;
import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.CieloTransactionNotFoundException;
import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.InvalidCieloTransactionException;
import br.edu.infnet.thomaspereiraapi.model.domain.repository.CieloPaymentProviderRepository;
import br.edu.infnet.thomaspereiraapi.model.domain.repository.CieloTransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CieloService implements CrudService <CieloTransaction, Integer > {

    private final CieloTransactionRepository cielotransactionRepository;
    private final CieloPaymentProviderRepository cieloPaymentProviderRepository;
    private final SellerService sellerService;
    private final CustomerService customerService;
    private final CieloZeroAuthFeignClient cieloZeroAuthFeignClient;

    CieloService(CieloTransactionRepository cielotransactionRepository, CieloPaymentProviderRepository cieloPaymentProviderRepository, SellerService sellerService,  CustomerService customerService,  CieloZeroAuthFeignClient cieloZeroAuthFeignClient) {
                this.cielotransactionRepository = cielotransactionRepository;
                this.cieloPaymentProviderRepository = cieloPaymentProviderRepository;
                this.sellerService = sellerService;
                this.customerService = customerService;
                this.cieloZeroAuthFeignClient = cieloZeroAuthFeignClient;
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> String WriteJson (Object objectToWrite) {

        this.objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        this.objectMapper.disable(MapperFeature.REQUIRE_HANDLERS_FOR_JAVA8_TIMES);
        this.objectMapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
        this.objectMapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY);

        try {
            return objectMapper.writeValueAsString(objectToWrite);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T ReadJson (String json, Class<T> classOfT) throws JsonProcessingException {
        return this.objectMapper.readValue(json, classOfT);
    }


    public void checkCieloTransaction(CieloTransaction cielotransaction) {
        if (Objects.isNull(cielotransaction)) {
            throw new IllegalArgumentException("Cielo transaction is null");
        }
        if (cielotransaction.getPayment() == null ) {
            throw new InvalidCieloTransactionException("Payment cannot be null");
        }
    }

    public String createCieloTransaction(Integer providerId, String providerKey, CieloTransaction transaction) {

        checkCieloTransaction(transaction);

        Seller seller = sellerService.getById(providerId);
        List<CieloTransaction> transactions = new ArrayList<>();
        seller.setTransactions(transactions);
        Optional<CieloPaymentProvider> cieloPaymentProvider = cieloPaymentProviderRepository.findById(providerId);
        CieloTransaction cielotransaction = transaction;
        cielotransaction.setStarteddate(LocalDateTime.now());
        cielotransaction.setMerchantId(cieloPaymentProvider.get().getProviderId());
        cielotransaction.setMerchantOrderId(transaction.getMerchantOrderId());

        Customer customer = customerService.getByCpf(transaction.getCustomer().getCpf());

        CreditCardDTO  creditCardDTO = new CreditCardDTO();
        creditCardDTO.copyFromCredicard(cielotransaction.getPayment().getCredicard());


        Boolean zeroAuthReturn = cieloZeroAuthFeignClient.checkCieloZeroAuth(creditCardDTO,cieloPaymentProvider.get().getProviderId(), cieloPaymentProvider.get().getProviderKey());

        if (!zeroAuthReturn) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid credcard details.");
        }

        var startDate = LocalDateTime.now();

        HttpClient client =  HttpClient.newHttpClient();

       var stringtransaction =  WriteJson(cielotransaction);

        HttpRequest request = HttpRequest.newBuilder()
                .header("MerchantId", cieloPaymentProvider.get().getProviderId())
                .header("MerchantKey",cieloPaymentProvider.get().getProviderKey())
                .header("Content-Type", "application/json")
                .uri(cieloPaymentProvider.get().getUrl())
                .POST(HttpRequest.BodyPublishers.ofString(stringtransaction))
                .timeout(Duration.ofSeconds(30))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            cielotransaction.setEndeddate(LocalDateTime.now());

            CieloTransaction transactionResponse = ReadJson(response.body(), CieloTransaction.class);
            transactionResponse.setStarteddate(startDate);
            transactionResponse.setSeller(seller);
            transactionResponse.setMerchantId(cieloPaymentProvider.get().getProviderId());
            transactionResponse.setMerchantName(cieloPaymentProvider.get().getName());
            transactionResponse.setCustomer(customer);
            transactionResponse.setEndeddate(LocalDateTime.now());
            transactions.add(transactionResponse);
            cielotransactionRepository.save(transactionResponse);

            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public CieloTransaction add(CieloTransaction transaction) {
        return cielotransactionRepository.save(transaction);
    }

    @Override
    public CieloTransaction update(Integer id, CieloTransaction transaction) {
        Optional <CieloTransaction> transaction1 = cielotransactionRepository.findById(id);
        transaction.setSeller(transaction1.get().getSeller());
        transaction.setId(id);
        transaction.setModifieddate(LocalDateTime.now());
        return cielotransactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        cielotransactionRepository.deleteById(id);
    }

    @Override
    public CieloTransaction getById(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        return cielotransactionRepository.findById(id).orElseThrow(
                () -> new CieloTransactionNotFoundException("Transaction with id: " + id + " can not be found.")
        );
    }

    public CieloTransaction getByMerchantOrderId(String merchantOrderId) {
        if(merchantOrderId == null) {
            throw new IllegalArgumentException("Merchant order id can't be null");
        }
        return cielotransactionRepository.findFirstByMerchantOrderId(merchantOrderId).orElseThrow(() -> new CieloTransactionNotFoundException("Transaction with MerchantOrderId: " + merchantOrderId + "not found."));
    }

    @Override
    public List<CieloTransaction> getList() {
        return cielotransactionRepository.findAll();
    }
}
