package progDyC.pdyc_tp2.events.util;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import progDyC.pdyc_tp2.model.Artista;
import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.repository.UserRepository;
import progDyC.pdyc_tp2.model.User;
import progDyC.pdyc_tp2.service.NotificationService;

/*
Propósito: “Escucha” los EventoStateChangedEvent publicados y envía notificaciones
 */

@Component
public class EventoStateChangedListener
        implements ApplicationListener<EventoStateChangedEvent> {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void onApplicationEvent(EventoStateChangedEvent evt) {
        Evento e = evt.getEvento();
        Set<Artista> artistas = e.getArtists();

        // Encuentra usuarios que siguen al menos uno de esos artistas
        List<User> seguidores = userRepo
                .findDistinctByArtistasSeguidosIn(artistas);

        // Construye el mensaje
        String mensaje = String.format(
                "El evento «%s» cambió de %s a %s.",
                e.getNombre(),
                evt.getOldState(),
                evt.getNewState()
        );

        // Envía la notificación
        seguidores.forEach(u ->
                notificationService.sendNotification(u, mensaje)
        );
    }
}
