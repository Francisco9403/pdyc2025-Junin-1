package progDyC.pdyc_tp2.service;

import java.time.LocalDate;
import java.util.List;

import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.model.EventoState;

public interface EventoService {
    List<Evento> getAll(EventoState state);
    Evento getById(Long id);
    Evento create(Evento evento);
    Evento update(Long id, Evento evento);
    void delete(Long id);

    Evento addArtist(Long eventId, Long artistId);
    Evento removeArtist(Long eventId, Long artistId);
    Evento confirm(Long id);
    Evento reschedule(Long id, LocalDate newDate);
    Evento cancel(Long id);
}
