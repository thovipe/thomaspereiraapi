package br.edu.infnet.thomaspereiraapi.model.domain;

import jakarta.persistence.*;
import java.net.URI;

@MappedSuperclass
public abstract class PaymentProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private URI url;
    private String providerId;
    private String providerKey;
    private boolean isActive;

    public abstract String getProviderInfo();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URI getUrl() {
        return this.url;
    }

    public void setUrl(URI url) {
        this.url = url;
    }

    public String getProviderId() {
        return this.providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderKey() {
        return this.providerKey;
    }

    public void setProviderKey(String providerKey) {
        this.providerKey = providerKey;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}

