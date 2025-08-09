package br.edu.infnet.thomaspereiraapi.controller;

import br.edu.infnet.thomaspereiraapi.model.domain.Seller;
import br.edu.infnet.thomaspereiraapi.model.service.SellerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @GetMapping
    public List<Seller> getSellers(){
        return sellerService.getList();
    }

    @GetMapping("/{id}")
    public Seller getSellerbyId(@PathVariable Integer id) {
        return sellerService.getById(id);
    }
}
