package br.edu.infnet.thomaspereiraapi.model.domain.exceptions;

public class CieloTransactionNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public CieloTransactionNotFoundException(String message) {
        super(message);
    }
}
