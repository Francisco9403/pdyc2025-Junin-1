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
                                                                                               //Leer de izquierda a derecha, un segundo (1000 mili.seg)*60= 1min, *60=1hr, *24=1dia, *10=10dias

        String token = JWT.create()
                .withSubject(subject) //Se incluye el subject (username) dentro del Payload del token
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
                                                                                       //Contrullo un verificador (de tipo JWTVerifier)
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(SECRET_KEY)).build(); //se le pasa el "algoritmo de firma" con el que va a estar firmado el token y que la secret key(del token) debeCoincidirConLaQueLePasemos
            verifier.verify(token); // se lanzara excepción si no es válido, firma valida, no expirado, bien formado (manejado por "verifier")
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

        return JWT.require(Algorithm.HMAC512(SECRET_KEY)) //se prepara el verificador con el algoritmo HMAC512 y la clave secreta usada para firmar el token.
                .build()
                .verify(token)  //se verifica que el token sea valido
                .getSubject(); //esta linea es la que devuelve lo que necesitamos
    }                          //Nota: se prodria agregar un bloque try catch por las dudas
}
