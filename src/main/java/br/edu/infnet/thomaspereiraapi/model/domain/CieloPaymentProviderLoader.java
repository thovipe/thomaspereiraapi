package br.edu.infnet.thomaspereiraapi.model.domain;

import br.edu.infnet.thomaspereiraapi.model.domain.repository.CieloPaymentProviderRepository;
import br.edu.infnet.thomaspereiraapi.model.service.SellerService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CieloPaymentProviderLoader implements ApplicationRunner {

    private final SellerService sellerService;
    private final CieloPaymentProviderRepository cieloPaymentProviderRepository;

    private final CieloPaymentProvider provider =  new CieloPaymentProvider();
    public CieloPaymentProviderLoader(SellerService sellerService, CieloPaymentProviderRepository cieloPaymentProviderRepository) {
        this.sellerService = sellerService;
        this.cieloPaymentProviderRepository = cieloPaymentProviderRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

            Seller seller = sellerService.getById(1);

            provider.setProviderId("");
            provider.setUri("https://apisandbox.cieloecommerce.cielo.com.br/1/sales/");
            provider.setName("Cielo Payment Provider");
            provider.setProviderKey("");
            provider.setActive(true);
            provider.setSeller(seller);
            seller.setCieloPaymentProvider(provider);
            sellerService.update(1,seller);
            cieloPaymentProviderRepository.save(provider);


    }

}
