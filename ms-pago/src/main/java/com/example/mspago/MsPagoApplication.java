package com.example.mspago;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
@EnableFeignClients
@SpringBootApplication
public class MsPagoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPagoApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("OPEN API MICROSERVICIO PAGO")
                .version("0.0.1")
                .description("microservicios web pago")
                .termsOfService("https:// swager.io/terms")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"))
        );
    }
}