package br.edu.infnet.thomaspereiraapi.model.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.net.URI;
import java.util.List;

@Entity
public class CieloPaymentProvider extends PaymentProvider {

    private URI queryUri;

    public URI getQueryUri() {
        return this.queryUri;
    }

    public void setQueryUri(URI queryUri) {
        this.queryUri = queryUri;
    }

    @Override
    public String getProviderInfo() {
        return String.format("ProviderName: %s ProviderId: %d", super.getName(), super.getProviderId() );
    }

    public void setUri(String url) {
        URI uri = URI.create(url);
        super.setUrl(uri);
    }
}
