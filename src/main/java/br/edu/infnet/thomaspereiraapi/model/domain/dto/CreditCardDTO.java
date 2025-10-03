package br.edu.infnet.thomaspereiraapi.model.domain.dto;

import br.edu.infnet.thomaspereiraapi.model.domain.Creditcard;

public class CreditCardDTO {

    private String creditCardNumber;
    private String holderName;
    private String expirationDate;
    private String securityCode;
    private String brand;

    public CreditCardDTO() {}

    public CreditCardDTO copyFromCredicard(Creditcard creditcard) {
        this.creditCardNumber = creditcard.getCardNumber();
        this.brand = creditcard.getBrand();
        this.expirationDate = creditcard.getExpirationDate();
        this.securityCode = creditcard.getSecurityCode().toString();
        this.holderName = creditcard.getHolder();
        return this;
    }


    public String getCreditCardNumber() {
        return this.creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getHolderName() {
        return this.holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSecurityCode() {
        return this.securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
