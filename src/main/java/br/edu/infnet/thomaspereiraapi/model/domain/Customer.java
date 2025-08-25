package br.edu.infnet.thomaspereiraapi.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The customer name is a mandatory field.")
    private String name;
    @NotBlank(message = "The customer email is a mandatory field.")
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "The customer phone number is a mandatory field.")
    @Pattern(regexp = "\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}", message = "Invalid phone number use the following pattern (XX) XXXXX-XXXX or (XX) XXXX-XXXX.")
    private String phone;
    @NotBlank(message = "The customer CPF is a mandatory field")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Invalid CPF, use the pattern XXX.XXX.XXX-XX.")
    private String cpf;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @Valid
    private Address address;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return String.format("costumerId: %d - name: %s - email: %s - phone: %s - address: %s", this.id, this.name, this.email, this.phone, this.address);
    }

}
