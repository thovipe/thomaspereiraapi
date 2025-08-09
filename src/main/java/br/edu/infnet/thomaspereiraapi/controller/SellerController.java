package br.edu.infnet.thomaspereiraapi.controller;

import br.edu.infnet.thomaspereiraapi.model.domain.Seller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    @GetMapping("/test")
    public Seller getSellerInfo() {

        Seller seller = new Seller();

        seller.setName("SuperStore");
        seller.setSellerId(1);
        seller.setIsActive(true);
        seller.setEmail("admin@superstore.com");

        return seller;
    }
}
