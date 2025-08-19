package br.edu.infnet.thomaspereiraapi.model.service;

import br.edu.infnet.thomaspereiraapi.model.domain.Seller;
import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.InvalidSellerExpection;
import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.SellerNotFoundException;
import br.edu.infnet.thomaspereiraapi.model.domain.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SellerService implements CrudService<Seller, Integer>{

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    private final Map<Integer, Seller> sellers = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    private void checkSeller(Seller seller){
        if(seller == null) {
            throw new IllegalArgumentException("Seller can not be null");
        }
        if(seller.getName() == null || seller.getName().trim().isEmpty()) {
            throw new InvalidSellerExpection("Seller name is required");
        }
    }

    @Override
    public Seller add(Seller seller) {
        checkSeller(seller);
        if(seller.getSellerId() != null && seller.getSellerId() != 0) {
            throw new IllegalArgumentException("Seller can not be added if has an id");
        }
        return sellerRepository.save(seller);
    }

    @Override
    public Seller update(Integer id, Seller seller) {
        checkSeller(seller);
        getById(id);
        seller.setSellerId(id);
        sellerRepository.findById(id);
        sellerRepository.save(seller);
        sellers.put(seller.getSellerId(), seller);
        return seller;
    }

    @Override
    public void delete(Integer integer) {
        if (integer == null || integer <= 0) {
            throw new IllegalArgumentException("Invalid Id, id needs to be greater than 0 and not null");
        }
        Seller byId = sellerRepository.getById(integer);
        sellerRepository.deleteById(integer);
    }

    @Override
    public Seller getById(Integer integer) {
        if (integer == null || integer <= 0) {
            throw new IllegalArgumentException("Invalid Id, id needs to be greater than 0 and not null");
        }
        Seller seller = sellers.get(integer);
        return sellerRepository.findById(integer).orElseThrow( () -> new SellerNotFoundException("Seller with id " + integer + " not found"));
    }

    @Override
    public List<Seller> getList() {
        return sellerRepository.findAll();
    }

    public Seller deactivateSeller(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid Id, id needs to be greater than 0 and not null");
        }
        Seller seller = getById(id);
        if(!seller.getIsActive()) {
            System.out.println("Seller: " + seller.getName() + " is already deactivated");
            return seller;
        }
        seller.setIsActive(false);
        return seller;
    }
}
