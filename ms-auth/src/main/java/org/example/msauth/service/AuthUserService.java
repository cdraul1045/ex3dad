package org.example.msauth.service;

import org.example.msauth.dto.AuthUserDto; // Importa el DTO para representar los datos del usuario.
import org.example.msauth.entity.AuthUser; // Importa la entidad AuthUser.
import org.example.msauth.entity.TokenDto; // Importa el DTO para representar el token.

public interface AuthUserService {

    // Método para guardar un nuevo usuario de autenticación.
    public AuthUser save(AuthUserDto authUserDto);

    // Método para realizar el login del usuario y obtener un token.
    public TokenDto login(AuthUserDto authUserDto);

    // Método para validar un token de autenticación.
    public TokenDto validate(String token);
}
