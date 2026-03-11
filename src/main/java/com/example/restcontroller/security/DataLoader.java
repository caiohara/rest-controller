package com.example.restcontroller.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataLoader {

    @Bean
    @Profile("!prod")
    public CommandLineRunner loadData(ApiKeyRepository apiKeyRepository) {
        return args -> {
            apiKeyRepository.save(new ApiKey("sk-example-key-12345", "Chave de Teste 1"));
            apiKeyRepository.save(new ApiKey("sk-test-key-67890", "Chave de Teste 2"));
        };
    }
}