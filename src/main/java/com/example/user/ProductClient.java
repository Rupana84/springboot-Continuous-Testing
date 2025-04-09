package com.example.user;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ProductClient {

    private final WebClient webClient;

    public ProductClient(WebClient.Builder webClientBuilder,
                         @Value("${PRODUCT_SERVICE_URL}") String productServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(productServiceUrl).build();
    }

    @PostConstruct
    public void logBaseUrl() {
        System.out.println("Product Service Base URL: " + webClient);
    }

    public Mono<String> fetchAllProducts() {
        return webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(ex -> {
                    ex.printStackTrace();  // or use a logger
                    return Mono.just("[]"); // empty JSON array fallback
                });
    }
}