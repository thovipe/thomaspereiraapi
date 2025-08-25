package br.edu.infnet.thomaspereiraapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

@Entity
public class Creditcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The credit card number is a mandatory field.")
    private String cardNumber;
    private String holder;
    //@Range(min = 3, max = 5, message = "The security code is a mandatory field.")
    private Integer securityCode;
    @NotBlank(message = "The expiration date is a mandatory field.")
    private String expirationDate;
    @NotBlank(message = "The creditcard brand is a mandatory field.")
    private String brand;

    public String getCardNumber() {
        return this.cardNumber;
    }
    public void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }
    public String getHolder() {
        return this.holder;
    }
    public void setHolder(String cardHolderName) {
        this.holder = holder;
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
    public String getBrand() {
        return this.brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
}
