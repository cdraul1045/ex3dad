package org.example.msauth.service.impl;

import org.example.msauth.dto.AuthUserDto; // Importa el DTO para representar los datos del usuario.
import org.example.msauth.entity.AuthUser; // Importa la entidad AuthUser.
import org.example.msauth.entity.TokenDto; // Importa el DTO para representar el token.
import org.example.msauth.repository.AuthUserRepository; // Importa el repositorio de usuarios.
import org.example.msauth.security.JwtProvider; // Importa el proveedor de JWT.
import org.example.msauth.service.AuthUserService; // Importa la interfaz de servicio.
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación de inyección de dependencias.
import org.springframework.security.crypto.password.PasswordEncoder; // Importa el codificador de contraseñas.
import org.springframework.stereotype.Service; // Importa la anotación de servicio.
import java.util.Optional; // Importa la clase Optional para manejar posibles valores nulos.

@Service // Indica que esta clase es un servicio de Spring.
public class AuthUserServiceImpl implements AuthUserService {
    @Autowired
    AuthUserRepository authUserRepository; // Repositorio para acceder a datos de AuthUser.

    @Autowired
    PasswordEncoder passwordEncoder; // Codificador para encriptar contraseñas.

    @Autowired
    JwtProvider jwtProvider; // Proveedor para manejar tokens JWT.

    @Override
    public AuthUser save(AuthUserDto authUserDto) {
        // Verifica si el usuario ya existe.
        Optional<AuthUser> user = authUserRepository.findByUserName(authUserDto.getUserName());
        if (user.isPresent())
            return null; // Retorna null si el usuario ya existe.

        // Encripta la contraseña.
        String password = passwordEncoder.encode(authUserDto.getPassword());

        // Crea una nueva instancia de AuthUser con el nombre de usuario y la contraseña encriptada.
        AuthUser authUser = AuthUser.builder()
                .userName(authUserDto.getUserName())
                .password(password)
                .build();

        // Guarda el nuevo usuario en la base de datos y retorna la entidad.
        return authUserRepository.save(authUser);
    }

    @Override
    public TokenDto login(AuthUserDto authUserDto) {
        // Busca el usuario por nombre de usuario.
        Optional<AuthUser> user = authUserRepository.findByUserName(authUserDto.getUserName());
        if (!user.isPresent())
            return null; // Retorna null si el usuario no existe.

        // Verifica si la contraseña ingresada coincide con la almacenada.
        if (passwordEncoder.matches(authUserDto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get())); // Retorna el token si la contraseña es correcta.

        return null; // Retorna null si la contraseña es incorrecta.
    }

    @Override
    public TokenDto validate(String token) {
        // Valida el token utilizando JwtProvider.
        if (!jwtProvider.validate(token))
            return null; // Retorna null si el token no es válido.

        // Extrae el nombre de usuario del token.
        String username = jwtProvider.getUserNameFromToken(token);
        if (!authUserRepository.findByUserName(username).isPresent())
            return null; // Retorna null si no se encuentra el usuario.

        return new TokenDto(token); // Retorna el token si es válido y el usuario existe.
    }
}
