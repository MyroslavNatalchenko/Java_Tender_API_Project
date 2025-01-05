package com.tender.tenderwebclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.tender"})
public class TenderWebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenderWebClientApplication.class, args);
	}

}
