package com.example.mscliente;

import io.swagger.v3.oas.models.OpenAPI; // Importa la clase OpenAPI para configurar Swagger
import io.swagger.v3.oas.models.info.Info; // Importa la clase Info para la información de la API
import io.swagger.v3.oas.models.info.License; // Importa la clase License para la información de la licencia
import org.springframework.boot.SpringApplication; // Importa la clase para iniciar la aplicación Spring
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa la anotación para la aplicación Spring Boot
import org.springframework.context.annotation.Bean; // Importa la anotación para definir un bean

@SpringBootApplication // Indica que esta clase es la configuración principal de la aplicación
public class MsClienteApplication {

    public static void main(String[] args) {
        // Inicia la aplicación Spring
        SpringApplication.run(MsClienteApplication.class, args);
    }

    @Bean // Define un bean que configura la documentación de la API
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("OPEN API MICROSERVICIO CLIENTE") // Título de la API
                .version("0.0.1") // Versión de la API
                .description("Servicios web para la gestión de clientes") // Descripción de la API
                .termsOfService("http://swagger.io/terms") // Términos de servicio
                .license(new License().name("Apache 2.0").url("http://springdoc.org")) // Información de la licencia
        );
    }
}
