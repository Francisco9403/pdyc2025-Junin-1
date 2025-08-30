package progDyC.pdyc_tp2.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import progDyC.pdyc_tp2.model.User;

/**
 * Servicio de notificaciones de prueba: NO envía mails,
 * solo escribe en consola/log para verificar que la llamada ocurre.
 * esta una implementacion secuncaria/optativa para ver que funcione el service
 */
@Primary///el primary prioriza hacer este y no el EmailNotificationServic
@Service
public class LoggingNotificationService implements NotificationService {

    @Override
    public void sendNotification(User user, String message) {
        // Aquí puedes usar un logger en vez de System.out si prefieres:
        System.out.println("[NOTIF] Para: " + user.getEmail()
                + " ▶ " + message);
    }
}
