package com.tender.tenderupdater;

import com.tender.tenderupdater.updater.IUpdateTenders;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.tender.tenderdatabase")
@SpringBootApplication(scanBasePackages = "com.tender")
public class TenderUpdaterApplication implements CommandLineRunner {
	private final IUpdateTenders updater;

	public TenderUpdaterApplication(IUpdateTenders updater) {
		this.updater = updater;
	}

	public static void main(String[] args) {
		SpringApplication.run(TenderUpdaterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		updater.UpdateByPage(1);
	}
}
