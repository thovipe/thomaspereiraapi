package br.edu.infnet.thomaspereiraapi.model.service;

import br.edu.infnet.thomaspereiraapi.model.domain.Seller;
import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.InvalidSellerExpection;
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

    @Override
    public Seller add(Seller seller) {
        if(seller.getName() == null || seller.getName().trim().isEmpty()) {
            throw new InvalidSellerExpection("Seller name is required");
        }
        seller.setSellerId(nextId.getAndIncrement());
        sellers.put(seller.getSellerId(), seller);
        return seller;
    }

    @Override
    public Seller update(Seller seller) {
        return null;
    }

    @Override
    public Seller delete(Integer integer) {
        return null;
    }

    @Override
    public Seller getById(Integer integer) {
        if (integer == null || integer <= 0) {
            throw new IllegalArgumentException("Invalid Id, id needs to be greater than 0 or not null");
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
}
