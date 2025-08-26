package br.edu.infnet.thomaspereiraapi.model.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
public class CieloTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The merchant order Id is mandatory field.")
    @JsonAlias("MerchantOrderId")
    private String merchantOrderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    @Valid
    @JsonAlias("Payment")
    private CieloPayment payment;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime starteddate;

    private LocalDateTime endeddate;
    private LocalDateTime modifieddate;
    private String merchantId;
    private String merchantName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    @Valid
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Seller seller;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

   public Customer getCustomer() {
        return this.customer;
    }

   public void setCustomer(Customer customer) {
       this.customer = customer;
   }

   public Seller getSeller() {
        return this.seller;
   }

   public void setSeller(Seller seller) {
        this.seller = seller;
   }

    @Override
    public String toString() {
        return super.toString();
   }
}
