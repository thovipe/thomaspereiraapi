package br.edu.infnet.thomaspereiraapi.controller;

import br.edu.infnet.thomaspereiraapi.model.domain.CieloTransaction;
import br.edu.infnet.thomaspereiraapi.model.service.CieloService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final CieloService cieloService;

    public TransactionController(CieloService cieloService) {
        this.cieloService = cieloService;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestHeader("ProviderId") Integer providerId,
                                                    @RequestHeader("ProviderKey") String providerKey,
                                                    @Valid @RequestBody CieloTransaction transaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cieloService.createCieloTransaction(providerId, providerKey, transaction));
    }



}
