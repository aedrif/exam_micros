package com.alibou.student;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductGraphQLClient {

    private final WebClient webClient;

    @Value("${product.service.url}")
    private String productServiceUrl;

    public ProductGraphQLClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(productServiceUrl).build();
    }

    public Mono<String> getProductDetails(String productId) {
        String graphqlQuery = "{ \"query\": \"{ product(id: \\\"" + productId + "\\\") { id name price } }\" }";

        return this.webClient.post()
                .uri("/graphql") // Assuming the endpoint is /graphql
                .bodyValue(graphqlQuery)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> Mono.just("Error: " + e.getMessage()));
    }
}

