package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class NdjsonApplication {

    @Value("${endpoint}")
    private String endpoint;

    public static void main(String[] args) {
        SpringApplication.run(NdjsonApplication.class, args);
    }

    @Bean
    public ApiInterface apiInterface() {
        var webClient = WebClient.builder()
                .baseUrl(endpoint)
                .build();
        var factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return factory.createClient(ApiInterface.class);
    }

}
