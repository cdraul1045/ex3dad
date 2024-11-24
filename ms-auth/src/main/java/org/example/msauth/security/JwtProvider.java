package org.example.msauth.security;

import io.jsonwebtoken.Jwts; // Importa la biblioteca para manejar JWT.
import io.jsonwebtoken.SignatureAlgorithm; // Importa los algoritmos de firma para JWT.
import org.example.msauth.entity.AuthUser; // Importa la entidad AuthUser.
import org.springframework.beans.factory.annotation.Value; // Para inyectar valores desde el archivo de configuración.
import org.springframework.stereotype.Component; // Marca la clase como un componente de Spring.
import javax.annotation.PostConstruct; // Para ejecutar código después de la inicialización.
import java.util.Base64; // Para codificar el secreto en Base64.
import java.util.Date; // Para manejar fechas.
import java.util.HashMap; // Para usar un mapa que contendrá los claims del token.
import java.util.Map; // Para usar el tipo Map.

@Component // Marca esta clase como un componente de Spring.
public class JwtProvider {
    @Value("${jwt.secret}") // Inyecta el secreto del JWT desde el archivo de configuración.
    private String secret;

    @PostConstruct // Método que se ejecuta después de que el componente ha sido inicializado.
    protected void init() {
        // Codifica el secreto en Base64 para mayor seguridad.
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    // Método para crear un token a partir de un objeto AuthUser.
    public String createToken(AuthUser authUser) {
        Map<String, Object> claims = new HashMap<>(); // Mapa para almacenar claims.
        // Establece el sujeto del token como el nombre de usuario.
        claims = Jwts.claims().setSubject(authUser.getUserName());
        // Agrega el ID del usuario como un claim adicional.
        claims.put("id", authUser.getId());
        Date now = new Date(); // Obtiene la fecha y hora actual.
        // Establece la fecha de expiración del token (1 hora a partir de ahora).
        Date exp = new Date(now.getTime() + 3600000);
        // Crea el token utilizando los claims, la fecha de emisión y expiración, y la firma.
        return Jwts.builder()
                .setClaims(claims) // Agrega los claims al token.
                .setIssuedAt(now) // Establece la fecha de emisión.
                .setExpiration(exp) // Establece la fecha de expiración.
                .signWith(SignatureAlgorithm.HS256, secret) // Firma el token con el secreto y el algoritmo especificado.
                .compact(); // Compacta el token en una cadena.
    }

    // Método para validar un token JWT.
    public boolean validate(String token) {
        try {
            // Intenta parsear el token con la clave de firma.
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true; // Si el token es válido, devuelve true.
        } catch (Exception e) {
            return false; // Si hay un error, el token es inválido.
        }
    }

    // Método para obtener el nombre de usuario del token.
    public String getUserNameFromToken(String token) {
        try {
            // Intenta extraer el sujeto (nombre de usuario) del token.
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            return "bad token"; // Si hay un error, devuelve un mensaje indicando que el token es malo.
        }
    }
}
