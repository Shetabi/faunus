package com.faunus.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration(proxyBeanMethods = false)
public class TestConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper(); // Default or custom configuration as needed
    }
}
