package com.example.msgatewayserver.config;

import com.example.msgatewayserver.dto.TokenDto; // DTO utilizado para manejar el token
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// Anotación que marca esta clase como un componente de Spring, lo que permite su detección automática.
@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    private WebClient.Builder webClient; // Constructor del WebClient para realizar solicitudes HTTP

    // Constructor que recibe un WebClient.Builder inyectado y llama al constructor de la superclase.
    public AuthFilter(WebClient.Builder webClient) {
        super(Config.class); // Indica que se usará la clase Config para la configuración del filtro.
        this.webClient = webClient; // Inicializa el WebClient.Builder.
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Devuelve un GatewayFilter que se aplicará a cada solicitud.
        return (((exchange, chain) -> {
            // Verifica si la solicitud contiene un encabezado de autorización.
            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                return onError(exchange, HttpStatus.BAD_REQUEST); // Respuesta de error si falta el encabezado.

            // Obtiene el valor del encabezado de autorización.
            String tokenHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String [] chunks = tokenHeader.split(" "); // Separa el encabezado en partes.

            // Verifica el formato del token.
            if(chunks.length != 2 || !chunks[0].equals("Bearer"))
                return onError(exchange, HttpStatus.BAD_REQUEST); // Respuesta de error si el formato es incorrecto.

            // Realiza una llamada al servicio de autenticación para validar el token.
            return webClient.build()
                    .post()
                    .uri("http://ms-auth-service/auth/validate?token=" + chunks[1]) // URI para validar el token.
                    .retrieve().bodyToMono(TokenDto.class) // Espera recibir un TokenDto como respuesta.
                    .map(t -> {
                        t.getToken(); // Se obtiene el token validado.
                        return exchange; // Retorna el intercambio de la solicitud.
                    }).flatMap(chain::filter); // Continúa con el siguiente filtro en la cadena.
        }));
    }

    // Método que maneja errores y envía una respuesta con el código de estado HTTP proporcionado.
    public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status){
        ServerHttpResponse response = exchange.getResponse(); // Obtiene la respuesta del intercambio.
        response.setStatusCode(status); // Establece el código de estado en la respuesta.
        return response.setComplete(); // Completa la respuesta.
    }

    // Clase interna que representa la configuración del filtro (puede ser utilizada para configurar el filtro si es necesario).
    public static class Config {}
}
