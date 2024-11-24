package com.example.msenvio;

import io.swagger.v3.oas.models.OpenAPI; // Importa la clase OpenAPI para configuración de Swagger
import io.swagger.v3.oas.models.info.Info; // Importa la clase Info para metadatos de la API
import io.swagger.v3.oas.models.info.License; // Importa la clase License para información de licencia
import org.springframework.boot.SpringApplication; // Importa SpringApplication para iniciar la aplicación
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa la anotación para la configuración automática de Spring Boot
import org.springframework.cloud.openfeign.EnableFeignClients; // Importa la anotación para habilitar Feign Clients
import org.springframework.context.annotation.Bean; // Importa la anotación para definir un bean

@EnableFeignClients // Habilita el uso de Feign Clients en la aplicación
@SpringBootApplication // Indica que esta es la clase principal de Spring Boot
public class MsEnvioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsEnvioApplication.class, args); // Inicia la aplicación
    }

    @Bean // Define un bean de configuración para OpenAPI
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info() // Configura la información de la API
                .title("OPEN API MICROSERVICIO ENVÍO") // Título de la API
                .version("0.0.1") // Versión de la API
                .description("servicios web envío") // Descripción de la API
                .termsOfService("http://swagger.io/terms") // URL de los términos de servicio
                .license(new License().name("Apache 2.0").url("http://springdoc.org")) // Información de licencia
        );
    }
}
