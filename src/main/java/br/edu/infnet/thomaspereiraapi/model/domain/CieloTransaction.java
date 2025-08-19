package br.edu.infnet.thomaspereiraapi.model.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class CieloTransaction extends Transaction {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "costumer_id")
    private Costumer costumer;
    private String merchantOrderId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private CieloPayment payment;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_seller_id")
    private Seller seller;

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

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

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
