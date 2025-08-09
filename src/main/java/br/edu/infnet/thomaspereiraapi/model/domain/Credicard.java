package br.edu.infnet.thomaspereiraapi.model.domain;

public class Credicard {

    private String cardNumber;
    private String cardHolderName;
    private int securityCode;
    private String expirationDate;

    public String getCardNumber() {
        return this.cardNumber;
    }
    public void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }
    public String getCardHolderName() {
        return this.cardHolderName;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    public int getSecurityCode() {
        return this.securityCode;
    }
    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }
    public String getExpirationDate() {
        return this.expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
