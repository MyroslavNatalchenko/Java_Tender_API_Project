package com.tender.tenderwebclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@ComponentScan
public class RestClientConfig {

    @Bean
    RestClient getRestClient() {
        return RestClient.builder().build();
    }
}
