package br.edu.infnet.thomaspereiraapi.model.domain.exceptions;

public class InvalidCieloTransactionException extends RuntimeException {
    public InvalidCieloTransactionException(String message) {
        super(message);
    }
}
