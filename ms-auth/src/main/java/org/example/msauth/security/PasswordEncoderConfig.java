package org.example.msauth.security;

import org.springframework.context.annotation.Bean; // Importa la anotación para declarar un bean.
import org.springframework.context.annotation.Configuration; // Importa la anotación para marcar una clase de configuración.
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Importa el codificador BCrypt.
import org.springframework.security.crypto.password.PasswordEncoder; // Importa la interfaz PasswordEncoder.

@Configuration // Marca la clase como una clase de configuración de Spring.
public class PasswordEncoderConfig {

    // Declara un bean de PasswordEncoder que será administrado por el contenedor de Spring.
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Retorna una nueva instancia de BCryptPasswordEncoder, que implementa la interfaz PasswordEncoder.
        return new BCryptPasswordEncoder();
    }
}
