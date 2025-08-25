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
    public void run(ApplicationArguments args) {

        try {
                provider.setProviderId("c9c79a84-f3fb-41b1-86b3-36d212d3068a");
                provider.setUri("https://apisandbox.cieloecommerce.cielo.com.br/1/sales/");
                provider.setName("Cielo Payment Provider");
                provider.setProviderKey("IRVVOZKOGEBQHVDHUMEIDPIZDTYEGJMMBAFHSIAL");
                provider.setActive(true);
                Seller seller = sellerService.getById(1);
                provider.setSeller(seller);
                sellerService.update(1, seller);
                cieloPaymentProviderRepository.save(provider);

        }  catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
