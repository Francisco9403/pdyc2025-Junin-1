package progDyC.pdyc_tp2.events.util;

import com.password4j.Password; // Esta importación es correcta
import com.password4j.Hash;    // Esta importación es correcta
import org.springframework.stereotype.Component;

// YA NO NECESITAS ESTA IMPORTACIÓN: import com.password4j.BcryptFunction;
@Component
public class PasswordEncoderUtil  {

    /**A
     * Recibe un password en texto plano y retorna un hash de Bcrypt.
     * Utilizar Password4j en la implementación.
     */
    public String encode(String rawPassword) {
        // Usa .withBcrypt() directamente. Password4j gestiona los detalles de Bcrypt internamente.
        Hash hash = Password.hash(rawPassword).withBcrypt();
        return hash.getResult();
    }

    /**
     * Recibe un password en texto plano y un password-hash y verifica que
     * sean válidos entre sí. Utilizar Password4j en la implementación.
     */
    public boolean verify(String rawPassword, String encodedPassword) {
        // Usa .withBcrypt() directamente para la verificación.
        return Password.check(rawPassword, encodedPassword).withBcrypt();
    }
}
