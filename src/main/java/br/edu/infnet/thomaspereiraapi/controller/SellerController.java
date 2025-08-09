package br.edu.infnet.thomaspereiraapi.controller;

import br.edu.infnet.thomaspereiraapi.model.domain.Seller;
import br.edu.infnet.thomaspereiraapi.model.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

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

    @PostMapping
    public Seller add(@RequestBody Seller seller){
        return sellerService.add(seller);
    }

    @PutMapping("/{id}")
    public Seller update(@PathVariable Integer id, @RequestBody Seller seller){
        return sellerService.update(id, seller);
    }

    @PatchMapping("/{id}/deactivate")
    public Seller deactivate(@PathVariable Integer id) {
        return sellerService.deactivateSeller(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        sellerService.delete(id);
    }
}
