package br.edu.infnet.thomaspereiraapi.model.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.net.URI;
import java.util.List;

@Entity
public class CieloPaymentProvider extends PaymentProvider {

    private URI queryUri;
    @ManyToOne(optional = false)
    private Seller seller;

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

    public Seller getSeller() {
        return this.seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

}
