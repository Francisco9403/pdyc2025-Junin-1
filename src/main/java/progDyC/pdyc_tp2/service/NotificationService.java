package progDyC.pdyc_tp2.service;

import progDyC.pdyc_tp2.model.User;

public interface NotificationService {
    /**
     * Envía una notificación (email, push, etc.) al usuario.
     */
    void sendNotification(User user, String message);
}
