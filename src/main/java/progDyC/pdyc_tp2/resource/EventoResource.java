package progDyC.pdyc_tp2.resource;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.model.EventoState;
import progDyC.pdyc_tp2.service.EventoService;

@RestController
@RequestMapping("/admin/events")
public class EventoResource {

    @Autowired
    private EventoService service;

    @GetMapping
    public List<Evento> getAll(@RequestParam(required = false) EventoState state) {
        return service.getAll(state);
    }

    @GetMapping("/{id}")
    public Evento getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Evento create(@RequestBody Evento evento) {
        return service.create(evento);
    }

    @PutMapping("/{id}")
    public Evento update(@PathVariable Long id, @RequestBody Evento evento) {
        return service.update(id, evento);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/{id}/artists")
    public Evento addArtist(@PathVariable Long id, @RequestBody Map<String, Long> body) {
        return service.addArtist(id, body.get("artistId"));
    }

    @DeleteMapping("/{id}/artists/{artistId}")
    public Evento removeArtist(@PathVariable Long id, @PathVariable Long artistId) {
        return service.removeArtist(id, artistId);
    }

    @PutMapping("/{id}/confirmed")
    public Evento confirm(@PathVariable Long id) {
        return service.confirm(id);
    }

    @PutMapping("/{id}/rescheduled")
    public Evento reschedule(
            @PathVariable Long id,
            @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        return service.reschedule(id, startDate);
    }

    @PutMapping("/{id}/canceled")
    public Evento cancel(@PathVariable Long id) {
        return service.cancel(id);
    }
}
