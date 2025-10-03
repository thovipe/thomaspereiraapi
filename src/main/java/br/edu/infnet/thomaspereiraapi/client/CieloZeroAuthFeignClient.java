package br.edu.infnet.thomaspereiraapi.client;

import br.edu.infnet.thomaspereiraapi.model.domain.dto.CreditCardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value="CieloZeroAuth", url = "${cielo.zeroauth.url}")
public interface CieloZeroAuthFeignClient {

    @PostMapping("/api/statementpayments/isvalid")
    Boolean checkCieloZeroAuth(@RequestBody CreditCardDTO creditcard, @RequestHeader("MerchantId") String merchantId, @RequestHeader("MerchantKey") String merchantKey);

}
