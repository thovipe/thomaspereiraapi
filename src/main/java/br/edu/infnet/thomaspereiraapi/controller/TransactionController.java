package br.edu.infnet.thomaspereiraapi.controller;

import br.edu.infnet.thomaspereiraapi.model.domain.CieloTransaction;
import br.edu.infnet.thomaspereiraapi.model.domain.Transaction;
import br.edu.infnet.thomaspereiraapi.model.service.CieloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final CieloService cieloService;

    public TransactionController(CieloService cieloService) {
        this.cieloService = cieloService;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestHeader("ProviderId") Integer providerId,
                                                    @RequestHeader("ProviderKey") String providerKey, @RequestBody CieloTransaction transaction)
                                                    throws IOException, InterruptedException {

        return ResponseEntity.status(HttpStatus.CREATED).body(cieloService.createCieloTransaction(providerId, providerKey, transaction));
    }



}
