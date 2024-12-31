package com.tender.tenderclient.client;

import org.springframework.beans.factory.annotation.Value;

public record TenderUriProvider(String baseUrl, String country) implements ITenderUriProvider{
    public TenderUriProvider(
            @Value("tenders.guru/api") String baseUrl,
            @Value("es") String country) {
        this.baseUrl = baseUrl;
        this.country = country;
    }
}
