package br.edu.infnet.thomaspereiraapi.model.domain.exceptions;

public class SellerNotFoundException extends RuntimeException {
    public SellerNotFoundException(String message) {
        super(message);
    }
}
