package br.edu.infnet.thomaspereiraapi.model.domain;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class CustomerLoader implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader customerfile = new FileReader("costumers.txt");
        BufferedReader customerreader = new BufferedReader(customerfile);
        String customerline = customerreader.readLine();
        String[] fields = null;
        while (customerline != null) {
            fields = customerline.split(";");
            Address address = new Address();
            Costumer costumer = new Costumer();
            costumer.setCostumerId(Integer.valueOf(fields[0]));
            costumer.setName(fields[1]);
            costumer.setEmail(fields[2]);
            costumer.setPhone(fields[3]);
            address.setStreetName(fields[4]);
            address.setNumber(Integer.valueOf(fields[5]));
            address.setDistrict(fields[6]);
            address.setCity(fields[7]);
            address.setState(fields[8]);
            address.setZipCode(fields[9]);
            address.setCountry(fields[10]);
            address.setComplement(fields[11]);
            costumer.setAddress(address);

            System.out.println(costumer);

            customerline = customerreader.readLine();
        }
    }
}
