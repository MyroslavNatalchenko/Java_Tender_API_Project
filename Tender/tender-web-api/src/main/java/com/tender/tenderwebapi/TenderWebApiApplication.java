package com.tender.tenderwebapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.tender.tenderdatabase")
@SpringBootApplication(scanBasePackages = {"com.tender"})
public class TenderWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenderWebApiApplication.class, args);
    }

}
