package progDyC.pdyc_tp2.events.util;

import org.springframework.context.ApplicationEvent;
import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.model.EventoState;
/*
Propósito: Es un evento de Spring que encapsula un cambio de estado en un Evento.
Guarda el objeto Evento, su estado anterior (oldState) y su nuevo estado (newState).
Se publica cada vez que confirmas, cancelas o reprogrames un evento.

 */
public class EventoStateChangedEvent extends ApplicationEvent {
    private final Evento evento;
    private final EventoState oldState;
    private final EventoState newState;

    public EventoStateChangedEvent(Object source,
                                   Evento evento,
                                   EventoState oldState,
                                   EventoState newState) {
        super(source);
        this.evento    = evento;
        this.oldState  = oldState;
        this.newState  = newState;
    }

    public Evento getEvento() { return evento; }
    public EventoState getOldState() { return oldState; }
    public EventoState getNewState() { return newState; }
}
