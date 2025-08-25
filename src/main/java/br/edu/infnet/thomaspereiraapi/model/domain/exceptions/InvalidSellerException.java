package br.edu.infnet.thomaspereiraapi.model.domain.exceptions;

public class InvalidSellerException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public InvalidSellerException(String message) {
        super(message);
    }
}
