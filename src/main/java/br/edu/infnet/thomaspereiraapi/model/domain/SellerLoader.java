package br.edu.infnet.thomaspereiraapi.model.domain;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class SellerLoader implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader file = new FileReader("sellers.txt");
        BufferedReader reader = new BufferedReader(file);
        String line = reader.readLine();
        String[] fields = null;
        while (line != null) {
            fields = line.split(";");

            Seller seller = new Seller();
            seller.setSellerId(Integer.valueOf(fields[0]));
            seller.setName(fields[1]);
            seller.setEmail(fields[2]);
            seller.setIsActive(Boolean.valueOf(fields[3]));
            System.out.println(seller);
            line = reader.readLine();
        }

    }
}
