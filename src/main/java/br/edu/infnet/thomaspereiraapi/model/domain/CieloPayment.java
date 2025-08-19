package br.edu.infnet.thomaspereiraapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CieloPayment extends Payment {


    private Boolean capture;
    private Integer serviceTaxAmount;
    private Boolean authenticated;
    private String interest;

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
}
