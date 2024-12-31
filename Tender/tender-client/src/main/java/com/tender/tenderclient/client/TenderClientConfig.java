package com.tender.tenderclient.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TenderClientConfig {

    @Bean
    public ITenderUriProvider TenderUriProvider(
            @Value("tenders.guru/api") String baseUrl,
            @Value("es") String country) {
        return new TenderUriProvider(baseUrl, country);
    }

    @Bean
    @Scope("prototype")
    public TendersClient moviesClient(ITenderUriProvider TenderUriProvider) {
        return new TendersClient(TenderUriProvider);
    }
}