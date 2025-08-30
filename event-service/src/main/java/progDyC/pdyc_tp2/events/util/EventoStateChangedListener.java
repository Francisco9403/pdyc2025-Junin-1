package progDyC.pdyc_tp2.events.util;
import java.util.HashSet;
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

        // 1) Usuarios que siguen a algún artista del evento
        List<User> seguidores = userRepo
                .findDistinctByArtistasSeguidosIn(artistas);

        // 2) Usuarios que tienen el evento en favoritos
        List<User> fanaticos = userRepo
                .findDistinctByEventosFavoritosContaining(e);

        // 3) Unimos ambos listados sin duplicados
        Set<User> destinatarios = new HashSet<>();
        destinatarios.addAll(seguidores);
        destinatarios.addAll(fanaticos);

        // 4) Construimos el mensaje
        String mensaje = String.format(
                "El evento «%s» cambió de %s a %s.",
                e.getNombre(),
                evt.getOldState(),
                evt.getNewState()
        );

        // 5) Enviamos notificación a todos
        destinatarios.forEach(u ->
                notificationService.sendNotification(u, mensaje)
        );
    }

}
