package br.edu.infnet.thomaspereiraapi.model.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Seller Not Found")
public class SellerNotFoundException extends RuntimeException {

    public SellerNotFoundException(String message) {
        super(message);
    }
}
