package br.edu.infnet.thomaspereiraapi.model.domain;

import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.InvalidSellerException;
import br.edu.infnet.thomaspereiraapi.model.domain.exceptions.SellerNotFoundException;
import br.edu.infnet.thomaspereiraapi.model.domain.repository.CostumerRespository;
import br.edu.infnet.thomaspereiraapi.model.domain.repository.SellerRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Order(2)
@Component
public class CustomerLoader implements ApplicationRunner {
    private final CostumerRespository costumerRespository;
    private final SellerRepository sellerRepository;
    public CustomerLoader(CostumerRespository costumerRespository, SellerRepository sellerRepository) {
        this.costumerRespository = costumerRespository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader customerfile = new FileReader("costumers.txt");
        BufferedReader customerreader = new BufferedReader(customerfile);
        String customerline = customerreader.readLine();
        String[] fields = null;

        Seller seller = null;

        while (customerline != null) {
            fields = customerline.split(";");

            String cnpj = fields[13];
            Address address = new Address();
            Customer customer = new Customer();

            try {
                seller = sellerRepository.findByCnpj(cnpj).orElseThrow(() -> new SellerNotFoundException("Seller with CNPJ: " + cnpj + " not found."));
                customer.setName(fields[1]);
                customer.setEmail(fields[2]);
                customer.setPhone(fields[3]);
                address.setStreetName(fields[4]);
                address.setNumber(Integer.valueOf(fields[5]));
                address.setDistrict(fields[6]);
                address.setCity(fields[7]);
                address.setState(fields[8]);
                address.setZipCode(fields[9]);
                address.setCountry(fields[10]);
                address.setComplement(fields[11]);
                customer.setAddress(address);
                customer.setCpf(fields[12]);
                customer.setSeller(seller);
                costumerRespository.save(customer);
                System.out.println(customer);
            } catch (SellerNotFoundException e) {
                System.out.println(e.getMessage());
            }
            customerline = customerreader.readLine();
        }
    }
}
