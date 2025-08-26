package br.edu.infnet.thomaspereiraapi.controller;

import br.edu.infnet.thomaspereiraapi.model.domain.CieloTransaction;
import br.edu.infnet.thomaspereiraapi.model.service.CieloService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final CieloService cieloService;

    public TransactionController(CieloService cieloService) {
        this.cieloService = cieloService;
    }

    @GetMapping
    public ResponseEntity<List<CieloTransaction>> getTransactions() {
        return ResponseEntity.ok(cieloService.getList());
    }

    @GetMapping("/{merchantOrderId}")
    public ResponseEntity<CieloTransaction> getTransactionByMerchantOrderId(@PathVariable String merchantOrderId) {
        return ResponseEntity.ok(cieloService.getByMerchantOrderId(merchantOrderId));
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestHeader("ProviderId") Integer providerId,
                                                    @RequestHeader("ProviderKey") String providerKey,
                                                    @Valid @RequestBody CieloTransaction transaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cieloService.createCieloTransaction(providerId, providerKey, transaction));
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<CieloTransaction> updateTransaction(@PathVariable Integer transactionId ,@Valid @RequestBody CieloTransaction transaction) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(cieloService.update(transactionId, transaction));
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer transactionId) {
        cieloService.delete(transactionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
