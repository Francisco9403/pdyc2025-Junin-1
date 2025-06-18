package progDyC.pdyc_tp2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import progDyC.pdyc_tp2.model.User;
/**
 * Servicio de notificaciones de prueba: envía mails,
 * Propósito: Implementación real que envía correos vía SMTP usando JavaMailSender.
 * necesaria la configuarcion en al application properties
 */
@Service
public class EmailNotificationService implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;  // Configúralo en application.properties

    @Override
    public void sendNotification(User user, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Notificación de cambio en tu evento seguido");
        email.setText(message);
        mailSender.send(email);
    }
}
