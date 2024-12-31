package com.tender.tenderdatabase;

import com.tender.tenderdatabase.repositories.TenderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TenderDatabaseApplication implements CommandLineRunner {
    private final TenderRepository repository;

    public TenderDatabaseApplication(TenderRepository repository) {this.repository = repository;}

    public static void main(String[] args) {
        SpringApplication.run(TenderDatabaseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.flush();
    }
}
