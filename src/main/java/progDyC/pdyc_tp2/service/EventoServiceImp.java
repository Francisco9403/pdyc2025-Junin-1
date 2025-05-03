package progDyC.pdyc_tp2.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import progDyC.pdyc_tp2.model.Artista;
import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.model.EventoState;
import progDyC.pdyc_tp2.repository.ArtistaRepository;
import progDyC.pdyc_tp2.repository.EventoRepository;

@Service
public class EventoServiceImp implements EventoService {

    @Autowired
    private EventoRepository eventoRepo;

    @Autowired
    private ArtistaRepository artistaRepo;

    @Override
    public List<Evento> getAll(EventoState state) {
        if (state != null) {
            return eventoRepo.findByState(state);
        }
        return eventoRepo.findAll();
    }

    @Override
    public Evento getById(Long id) {
        return eventoRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Evento create(Evento evento) {
        evento.setState(EventoState.TENTATIVE); //Ver si son necesarias estas dos lineas porque se supone
        evento.getArtists().clear();            //Que cuando se crea el evento, ya contiene el estado y el 
        return eventoRepo.save(evento);         //Artista que queremos
    }

    @Override
    public Evento update(Long id, Evento evento) {
        Evento e = eventoRepo.findById(id).get();
        if (e.getState() != EventoState.TENTATIVE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Solo eventos tentativos pueden editarse");
        }
        e.setNombre(evento.getNombre());
        e.setDescripcion(evento.getDescripcion());
        e.setStartDate(evento.getStartDate());
        return eventoRepo.save(e);
    }

    @Override
    public void delete(Long id) {
        Evento e = eventoRepo.findById(id).get();
        if (e.getState() != EventoState.TENTATIVE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Solo eventos tentativos pueden borrarse");
        }
        eventoRepo.delete(e);
    }

    @Override
    public Evento addArtist(Long eventId, Long artistId) {
        Evento e = eventoRepo.findById(eventId).get();
        if (e.getState() != EventoState.TENTATIVE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Artista a = artistaRepo.findById(artistId)          //Ver si no necesita un .get() al final
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!a.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Artista inactivo");
        }
        e.getArtists().add(a);
        return eventoRepo.save(e);
    }

    @Override
    public Evento removeArtist(Long eventId, Long artistId) {
        Evento e = eventoRepo.findById(eventId).get();
        if (e.getState() != EventoState.TENTATIVE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        e.getArtists().removeIf(a -> a.getId().equals(artistId));
        return eventoRepo.save(e);
    }

    @Override
    public Evento confirm(Long id) {
        Evento e = eventoRepo.findById(id).get();
        if (e.getStartDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha debe ser futura");
        }
        e.setState(EventoState.CONFIRMED);
        return eventoRepo.save(e);
    }

    @Override
    public Evento reschedule(Long id, LocalDate newDate) {
        Evento e = eventoRepo.findById(id).get();
        if (!(e.getState() == EventoState.CONFIRMED || e.getState() == EventoState.RESCHEDULED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (newDate.isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nueva fecha debe ser futura");
        }
        e.setStartDate(newDate);
        e.setState(EventoState.RESCHEDULED);
        return eventoRepo.save(e);
    }

    @Override
    public Evento cancel(Long id) {
        Evento e = eventoRepo.findById(id).get();
        if (!(e.getState() == EventoState.CONFIRMED || e.getState() == EventoState.RESCHEDULED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        e.setState(EventoState.CANCELLED);
        return eventoRepo.save(e);
    }
}