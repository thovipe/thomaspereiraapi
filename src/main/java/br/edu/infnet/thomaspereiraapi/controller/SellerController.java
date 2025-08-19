package br.edu.infnet.thomaspereiraapi.controller;

import br.edu.infnet.thomaspereiraapi.model.domain.Seller;
import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.SellerNotFoundException;
import br.edu.infnet.thomaspereiraapi.model.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getSellers(){
        return ResponseEntity.ok(sellerService.getList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerbyId(@PathVariable Integer id) {
        return ResponseEntity.ok(sellerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Seller> add(@RequestBody Seller seller){
        Seller newSeller = sellerService.add(seller);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSeller);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seller> update(@PathVariable Integer id, @RequestBody Seller seller){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(sellerService.update(id, seller));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Seller> deactivate(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(sellerService.deactivateSeller(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        sellerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
