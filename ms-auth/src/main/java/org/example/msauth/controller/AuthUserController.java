package org.example.msauth.controller;

import org.example.msauth.dto.AuthUserDto; // Importa el DTO para representar los datos del usuario.
import org.example.msauth.entity.AuthUser; // Importa la entidad AuthUser.
import org.example.msauth.entity.TokenDto; // Importa el DTO para representar el token.
import org.example.msauth.service.AuthUserService; // Importa el servicio de usuarios de autenticación.
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación para inyección de dependencias.
import org.springframework.http.ResponseEntity; // Importa la clase para construir respuestas HTTP.
import org.springframework.web.bind.annotation.*; // Importa las anotaciones de Spring para manejar solicitudes HTTP.

@RestController // Indica que esta clase es un controlador REST.
@RequestMapping("/auth") // Define la ruta base para todas las peticiones en este controlador.
public class AuthUserController {
    @Autowired
    AuthUserService authUserService; // Servicio de autenticación inyectado.

    @PostMapping("/login") // Mapea las solicitudes POST a /auth/login.
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto authUserDto) {
        TokenDto tokenDto = authUserService.login(authUserDto); // Llama al servicio para autenticar al usuario.
        if (tokenDto == null)
            return ResponseEntity.badRequest().build(); // Retorna 400 si las credenciales son incorrectas.
        return ResponseEntity.ok(tokenDto); // Retorna 200 OK con el token si la autenticación es exitosa.
    }

    @PostMapping("/validate") // Mapea las solicitudes POST a /auth/validate.
    public ResponseEntity<TokenDto> validate(@RequestParam String token) {
        TokenDto tokenDto = authUserService.validate(token); // Llama al servicio para validar el token.
        if (tokenDto == null)
            return ResponseEntity.badRequest().build(); // Retorna 400 si el token no es válido.
        return ResponseEntity.ok(tokenDto); // Retorna 200 OK con el token si la validación es exitosa.
    }

    @PostMapping("/create") // Mapea las solicitudes POST a /auth/create.
    public ResponseEntity<AuthUser> create(@RequestBody AuthUserDto authUserDto) {
        AuthUser authUser = authUserService.save(authUserDto); // Llama al servicio para registrar un nuevo usuario.
        if (authUser == null)
            return ResponseEntity.badRequest().build(); // Retorna 400 si el usuario ya existe.
        return ResponseEntity.ok(authUser); // Retorna 200 OK con el usuario creado si la operación es exitosa.
    }
}
