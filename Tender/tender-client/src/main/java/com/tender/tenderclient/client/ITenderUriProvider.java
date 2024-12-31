package com.tender.tenderclient.client;

import org.springframework.web.util.UriComponentsBuilder;

public interface ITenderUriProvider {
    String country();
    String baseUrl();

    default UriComponentsBuilder builder(){
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(baseUrl())
                .pathSegment(country());
    }

}
