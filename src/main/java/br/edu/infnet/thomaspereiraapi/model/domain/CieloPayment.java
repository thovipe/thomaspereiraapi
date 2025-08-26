package br.edu.infnet.thomaspereiraapi.model.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.Entity;

@Entity
public class CieloPayment extends Payment {

    @JsonAlias("Capture")
    private Boolean capture;
    @JsonAlias("ServiceTaxAmount")
    private Integer serviceTaxAmount;
    @JsonAlias("Authenticate")
    private Boolean authenticated;
    @JsonAlias("Interest")
    private String interest;
    @JsonAlias("PaymentId")
    private String cieloPaymentId;
    @JsonAlias("Tid")
    private String acquirerTransactionId;

    public Boolean getCapture() {
        return capture;
    }

    public void setCapture(Boolean capture) {
        this.capture = capture;
    }

    public Integer getServiceTaxAmount() {
        return serviceTaxAmount;
    }

    public void setServiceTaxAmount(Integer serviceTaxAmount) {
        this.serviceTaxAmount = serviceTaxAmount;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getCieloPaymentId() {
        return this.cieloPaymentId;
    }

    public void setCieloPaymentId(String cieloPaymentId) {
        this.cieloPaymentId = cieloPaymentId;
    }

    public String getAcquirerTransactionId() {
        return this.acquirerTransactionId;
    }

    public void setAcquirerTransactionId(String acquirerTransactionId) {
        this.acquirerTransactionId = acquirerTransactionId;
    }
}
