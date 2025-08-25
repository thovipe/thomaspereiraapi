package br.edu.infnet.thomaspereiraapi.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currency;
    private Integer amount;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name= "creditcard_id")
    @JsonProperty("CreditCard")
    private Creditcard creditcard;
    private Integer installments;
    @JsonProperty("Type")
    @NotBlank(message = "The transaction type is mandatory field.")
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Creditcard getCredicard() {
        return creditcard;
    }

    public void setCredicard(Creditcard creditcard) {
        this.creditcard = creditcard;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
