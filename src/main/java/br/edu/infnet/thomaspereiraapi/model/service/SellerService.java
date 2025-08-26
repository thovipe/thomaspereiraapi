package br.edu.infnet.thomaspereiraapi.model.service;

import br.edu.infnet.thomaspereiraapi.model.domain.Seller;
import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.InvalidSellerException;
import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.SellerNotFoundException;
import br.edu.infnet.thomaspereiraapi.model.domain.repository.SellerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService implements CrudService<Seller, Integer>{

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    private void checkSeller(Seller seller){
        if(seller == null) {
            throw new IllegalArgumentException("Seller can not be null");
        }
        if(seller.getName() == null || seller.getName().trim().isEmpty()) {
            throw new InvalidSellerException("Seller name is required");
        }
        if(seller.getCnpj() == null || seller.getCnpj().trim().isEmpty()) {
            throw new InvalidSellerException("Seller CNPJ is required");
        }
    }

    @Override
    @Transactional
    public Seller add(Seller seller) {
        checkSeller(seller);
        if(seller.getId() != null && seller.getId() != 0) {
            throw new IllegalArgumentException("Seller can not be added if has an id");
        }
        return sellerRepository.save(seller);
    }

    @Override
    @Transactional
    public Seller update(Integer id, Seller seller) {
        checkSeller(seller);
        Seller sellerTobeUpdated = getById(id);
        sellerTobeUpdated.setName(seller.getName());
        sellerTobeUpdated.setCnpj(seller.getCnpj());
        sellerTobeUpdated.setAddress(seller.getAddress());
        sellerTobeUpdated.setEmail(seller.getEmail());
        sellerTobeUpdated.setIsActive(seller.getIsActive());
        return sellerRepository.save(sellerTobeUpdated);
    }

    @Override
    @Transactional
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
        sellerRepository.save(seller);
        return seller;
    }
}
