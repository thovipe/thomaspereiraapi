package br.edu.infnet.thomaspereiraapi.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Entity
public class CieloTransaction extends Transaction {

    @NotBlank(message = "The merchant order Id is mandatory field.")
    private String merchantOrderId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    @Valid
    private CieloPayment payment;
    private Integer sellerId;

    public String getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(String merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public CieloPayment getPayment() {
        return payment;
    }

    public void setPayment(CieloPayment payment) {
        this.payment = payment;
    }

    public Integer getSellerId() {
        return this.sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }


    @Override
    public String toString() {
        return super.toString();
   }
}
