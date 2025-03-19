package com.l4gger.client_virtual_threads.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class ClientDemoController {

    private final RestClient restClient;
    private final WebClient webClient;

    public ClientDemoController(RestClient.Builder builder, WebClient.Builder builderFlux) {
        this.restClient = builder.
                baseUrl("http://localhost:8081").build();
        this.webClient = builderFlux.
                baseUrl("http://localhost:8081").build();
    }

    @GetMapping("client")
    public String client() {
        log.info("Request Thread {} - {}", Thread.currentThread().threadId(), Thread.currentThread().getName());
        this.restClient.get()
                        .uri("/demo-service")
                                .retrieve()
                                        .toBodilessEntity();
        log.info("Response Thread {} - {}", Thread.currentThread().threadId(), Thread.currentThread().getName());

        return Thread.currentThread().toString();
    }

    @GetMapping("client-flux")
    public String clientFlux() {
        log.info("Request Thread {} - {}", Thread.currentThread().threadId(), Thread.currentThread().getName());
        Mono<Void> result = this.webClient.get()
                .uri("/demo-service")
                .exchangeToMono(response -> {
                    log.info("Response Thread {} - {}", Thread.currentThread().threadId(), Thread.currentThread().getName());
                    return Mono.empty();
                });
        result.subscribe();
        return Thread.currentThread().toString();
    }
}
