package org.example.msauth.security;

import org.springframework.context.annotation.Configuration; // Importa la anotación para marcar una clase de configuración.
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // Importa la clase para configurar seguridad web.
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; // Importa la anotación para habilitar la seguridad web.
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; // Importa la clase base para la configuración de seguridad web.

@Configuration // Marca la clase como una clase de configuración de Spring.
@EnableWebSecurity // Habilita la seguridad en la configuración web.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Desactiva la protección CSRF (Cross-Site Request Forgery)
        http.csrf().disable()
                // Configura las solicitudes autorizadas
                .authorizeRequests()
                // Permite el acceso a cualquier solicitud sin restricciones
                .anyRequest().permitAll();
    }
}
