package com.tender.tenderclient;

import com.tender.tenderclient.client.ITendersClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TenderClientApplication implements CommandLineRunner {

	ITendersClient client;

	public TenderClientApplication(ITendersClient client) {this.client = client;}

	public static void main(String[] args) {
		SpringApplication.run(TenderClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main.CheckItOut(client);
	}
}
