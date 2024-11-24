package com.example.msproveedor;

import io.swagger.v3.oas.models.OpenAPI; // Importa la clase para definir la configuración de OpenAPI
import io.swagger.v3.oas.models.info.Info; // Importa la clase para información del API
import io.swagger.v3.oas.models.info.License; // Importa la clase para definir licencias
import org.springframework.boot.SpringApplication; // Importa la clase para iniciar la aplicación Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa la anotación para configuración de Spring Boot
import org.springframework.context.annotation.Bean; // Importa la anotación para definir un bean

@SpringBootApplication // Indica que esta es una aplicación Spring Boot
public class MsProveedorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsProveedorApplication.class, args); // Inicia la aplicación
    }

    @Bean // Define un bean que configura OpenAPI
    public OpenAPI custoOpenAPI() {
        return new OpenAPI().info(new Info() // Crea un nuevo objeto OpenAPI
                .title("OPEN API MICROSERVICIO PROVEEDOR") // Título de la API
                .version("0.0.1") // Versión de la API
                .description("servicio web inventario") // Descripción de la API
                .termsOfService("http://swagger.io/terms") // Términos de servicio
                .license(new License().name("Apache 2.0").url("http://springdoc.org")) // Licencia de la API
        );
    }
}
