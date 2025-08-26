package br.edu.infnet.thomaspereiraapi.model.domain;

import br.edu.infnet.thomaspereiraapi.model.service.SellerService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Order(1)
@Component
public class SellerLoader implements ApplicationRunner {

    private final SellerService sellerService;

    public SellerLoader(SellerService sellerService){
        this.sellerService = sellerService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader file = new FileReader("sellers.txt");
        BufferedReader reader = new BufferedReader(file);
        String line = reader.readLine();
        String[] fields = null;
        while (line != null) {
            fields = line.split(";");

            Seller seller = new Seller();
            Address selleraddress = new Address();
            seller.setName(fields[1]);
            seller.setEmail(fields[2]);
            seller.setIsActive(Boolean.valueOf(fields[3]));
            selleraddress.setStreetName(fields[4]);
            selleraddress.setNumber(Integer.valueOf(fields[5]));
            selleraddress.setDistrict(fields[6]);
            selleraddress.setCity(fields[7]);
            selleraddress.setState(fields[8]);
            selleraddress.setZipCode(fields[9]);
            selleraddress.setCountry(fields[10]);
            selleraddress.setComplement(fields[11]);
            seller.setAddress(selleraddress);
            seller.setCnpj(fields[12]);
            sellerService.add(seller);
            System.out.println(seller);
            line = reader.readLine();
        }
        reader.close();
        System.out.println("List size: " + sellerService.getList().size());

    }
}
