package com.github.nicolasperuch.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
