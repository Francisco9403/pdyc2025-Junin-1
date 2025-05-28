package progDyC.pdyc_tp2.events.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil { 

    // Clave secreta para firmar el token, elijo una yo (debe mantenerse segura)
    //me recomiendan que cargue esta contraseña desde configuracion(application.properties o variables de entorno)
    private static final String SECRET_KEY = "mate_amargo123"; 

    public String generateToken(String subject){
        Date expirationDate = new Date(System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000); // Fecha de expiración: 10 días desde ahora

        String token = JWT.create()
                .withSubject(subject)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC512(SECRET_KEY));

        return "Bearer " + token;
    }

    public boolean verify(String token){
        try {
            // Eliminar el prefijo "Bearer " si está presente
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(SECRET_KEY)).build();
            verifier.verify(token); // lanza excepción si no es válido
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getSubject(String token){
         // Quitar el prefijo "Bearer " si está presente
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        return JWT.require(Algorithm.HMAC512(SECRET_KEY))
                .build()
                .verify(token)
                .getSubject(); //esta linea es la que devuelve lo que necesitamos
    }

}
