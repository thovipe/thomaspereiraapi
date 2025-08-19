package br.edu.infnet.thomaspereiraapi.model.service;

import br.edu.infnet.thomaspereiraapi.model.domain.*;
import br.edu.infnet.thomaspereiraapi.model.domain.repository.CieloPaymentProviderRepository;
import br.edu.infnet.thomaspereiraapi.model.domain.repository.CieloTransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CieloService implements CrudService <CieloTransaction, Integer > {

    private final CieloTransactionRepository cielotransactionRepository;
    private final CieloPaymentProviderRepository cieloPaymentProviderRepository;

    CieloService(CieloTransactionRepository cielotransactionRepository,  CieloPaymentProviderRepository cieloPaymentProviderRepository) {
                this.cielotransactionRepository = cielotransactionRepository;
                this.cieloPaymentProviderRepository = cieloPaymentProviderRepository;
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

    public String createCieloTransaction(Integer providerId, String providerKey, CieloTransaction transaction) throws IOException, InterruptedException {

        Optional<CieloPaymentProvider> cieloPaymentProvider = cieloPaymentProviderRepository.findById(providerId);

        CieloTransaction cielotransaction = transaction;
        cielotransaction.setStarteddate(LocalDateTime.now());
        cielotransaction.setMerchantId(cieloPaymentProvider.get().getProviderId());
        cielotransaction.setMerchantOrderId(UUID.randomUUID().toString());

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

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        cielotransaction.setEndeddate(LocalDateTime.now());
        add(cielotransaction);
        return response.body();
    }

    @Override
    public CieloTransaction add(CieloTransaction transaction) {

        cielotransactionRepository.save(transaction);

        return transaction;
    }

    @Override
    public CieloTransaction update(Integer integer, CieloTransaction entity) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public CieloTransaction getById(Integer integer) {
        return null;
    }

    @Override
    public List<CieloTransaction> getList() {
        return List.of();
    }
}
