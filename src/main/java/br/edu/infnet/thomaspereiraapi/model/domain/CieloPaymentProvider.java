package br.edu.infnet.thomaspereiraapi.model.domain;

import java.net.URI;

public class CieloPaymentProvider extends PaymentProvider {

    private URI queryUri;

    public URI getQueryUri() {
        return this.queryUri;
    }

    @Override
    public String getProviderInfo() {
        return String.format("ProviderName: %s ProviderId: %d", super.getName(), super.getProviderId() );
    }
}
