package br.edu.infnet.thomaspereiraapi.model.domain.exceptions;

public class InvalidSellerExpection extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public InvalidSellerExpection(String message) {
        super(message);
    }
}
