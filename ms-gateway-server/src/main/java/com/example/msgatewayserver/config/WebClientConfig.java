package com.example.msgatewayserver.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration // Indica que esta clase es una clase de configuración de Spring.
public class WebClientConfig {

    // Define un bean para crear un WebClient.Builder.
    // @LoadBalanced habilita el balanceo de carga, lo que permite que WebClient resuelva nombres de servicios registrados en Eureka.
    @Bean
    @LoadBalanced
    public WebClient.Builder builder() {
        // Retorna un builder para WebClient, que es el cliente HTTP reactivo de Spring.
        return WebClient.builder();
    }
}
