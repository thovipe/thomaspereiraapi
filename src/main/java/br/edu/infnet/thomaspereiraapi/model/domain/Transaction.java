package br.edu.infnet.thomaspereiraapi.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.springframework.boot.jackson.JsonComponent;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime starteddate;
    private LocalDateTime endeddate;
    private LocalDateTime modifieddate;
    private String merchantId;
    private String merchantName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @Valid
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStarteddate() {
        return starteddate;
    }

    public void setStarteddate(LocalDateTime starteddate) {
        this.starteddate = starteddate;
    }

    public LocalDateTime getEndeddate() {
        return endeddate;
    }

    public void setEndeddate(LocalDateTime endeddate) {
        this.endeddate = endeddate;
    }

    public LocalDateTime getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(LocalDateTime modifieddate) {
        this.modifieddate = modifieddate;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Customer getCostumer() {
        return this.customer;
    }

    public void setCostumer(Customer customer) {
        this.customer = customer;
    }
}
