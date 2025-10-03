package br.edu.infnet.thomaspereiraapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ThomaspereiraapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThomaspereiraapiApplication.class, args);
    }

}
