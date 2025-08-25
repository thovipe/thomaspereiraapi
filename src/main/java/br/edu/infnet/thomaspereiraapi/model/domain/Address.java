package br.edu.infnet.thomaspereiraapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "The street name is a mandatory field.")
    @Size(min = 2, max = 100, message = "The street name should have at least 2 characters and a max of 100.")
    private String streetName;
    @NotBlank(message = "The district is a mandatory field.")
    private String district;
    @NotBlank(message = "The city is a mandatory field.")
    private String city;
    @NotBlank(message = "The state is mandatory field.")
    private String state;
    @NotBlank(message = "The zipcode is mandatory field.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Invalid format, use the following pattern XXXXX-XXX")
    private String zipCode;
    @NotBlank(message = "The country is a mandatory field.")
    private String country;
    private String complement;
    private int number;

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getComplement() {
        return this.complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", number=" + number +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", complement='" + complement + '\'' +
                '}';
    }
}

