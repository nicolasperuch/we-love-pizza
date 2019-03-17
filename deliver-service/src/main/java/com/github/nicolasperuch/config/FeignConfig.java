package com.github.nicolasperuch.config;

import com.github.nicolasperuch.client.EventStoreClient;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig extends EventStoreConfig {

    @Bean
    public EventStoreClient eventStoreClient(){
        return Feign
                .builder()
                .target(EventStoreClient.class, buildEventStoreUri());
    }

}
