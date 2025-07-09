package br.com.postechfiap.medicamentosservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients(basePackages = "br.com.postechfiap.medicamentosservice")
public class MedicamentosServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicamentosServiceApplication.class, args);
    }

}
