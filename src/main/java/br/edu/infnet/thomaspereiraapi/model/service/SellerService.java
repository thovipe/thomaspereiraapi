package br.edu.infnet.thomaspereiraapi.model.service;

import br.edu.infnet.thomaspereiraapi.model.domain.Seller;
import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.InvalidSellerExpection;
import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.SellerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SellerService implements CrudService<Seller, Integer>{

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
        seller.setSellerId(nextId.getAndIncrement());
        sellers.put(seller.getSellerId(), seller);
        return seller;
    }

    @Override
    public Seller update(Integer id, Seller seller) {
        checkSeller(seller);
        getById(id);
        seller.setSellerId(id);
        sellers.put(seller.getSellerId(), seller);
        return seller;
    }

    @Override
    public void delete(Integer integer) {
        if (integer == null || integer <= 0) {
            throw new IllegalArgumentException("Invalid Id, id needs to be greater than 0 and not null");
        }
        if (!sellers.containsKey(integer)) {
            throw new SellerNotFoundException("Seller with id " + integer + " not found");
        }
        sellers.remove(integer);
    }

    @Override
    public Seller getById(Integer integer) {
        if (integer == null || integer <= 0) {
            throw new IllegalArgumentException("Invalid Id, id needs to be greater than 0 and not null");
        }
        Seller seller = sellers.get(integer);
        return seller;
    }

    @Override
    public List<Seller> getList() {
        ArrayList<Seller> sellerslist = new ArrayList<>();
        sellerslist.addAll(sellers.values());
        return sellerslist;
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
