package br.edu.infnet.thomaspereiraapi.model.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonAlias("Currency")
    private String currency;
    @NotNull(message = "The amount value can not be null.")
    @JsonAlias("Amount")
    private Integer amount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "creditcard_id")
    @JsonProperty("CreditCard")
    private Creditcard creditcard;
    @JsonAlias("Installments")
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
