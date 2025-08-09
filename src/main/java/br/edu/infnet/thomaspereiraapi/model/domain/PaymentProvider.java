package br.edu.infnet.thomaspereiraapi.model.domain;

import java.net.URI;

public class PaymentProvider {

    private String name;
    private URI url;
    private int providerId;
    private String providerKey;
    private boolean isActive;

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

    public int getProviderId() {
        return this.providerId;
    }

    public void setProviderId(int providerId) {
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

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}

