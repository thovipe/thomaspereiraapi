package br.edu.infnet.thomaspereiraapi.model.service;

import br.edu.infnet.thomaspereiraapi.model.domain.Seller;
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
    public Seller get() {
        Seller seller = new Seller();

        seller.setName("SuperStore");
        seller.setSellerId(1);
        seller.setIsActive(true);
        seller.setEmail("admin@superstore.com");

        return seller;
    }

    @Override
    public List<Seller> getList() {
        ArrayList<Seller> sellerslist = new ArrayList<>();
        sellerslist.addAll(sellers.values());
        return sellerslist;
    }
}
