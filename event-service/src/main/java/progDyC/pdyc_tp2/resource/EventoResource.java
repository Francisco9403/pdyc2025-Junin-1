package progDyC.pdyc_tp2.resource;

import java.time.LocalDate;
//import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.model.EventoState;
import progDyC.pdyc_tp2.service.AdminAuthorizationService;
import progDyC.pdyc_tp2.service.EventoService;

@RestController
@RequestMapping("/admin/events")
public class EventoResource {

    @Autowired
    private EventoService eventoService;
    
    @Autowired
    private AdminAuthorizationService authorizationService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) EventoState state,
                                @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(eventoService.getAll(state));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id,
                                     @RequestHeader("Authorizathion") String token) {
        try{
            authorizationService.authorize(token);
            Evento evento = eventoService.getById(id);
            return ResponseEntity.ok(evento);
        } catch (Exception e) {
            return ResponseEntity.status(403).build(); // Acceso denegado
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Evento evento,
                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            Evento creado = eventoService.create(evento);
            return ResponseEntity.ok(creado);
        } catch (Exception e) {
            return ResponseEntity.status(403).build(); // Acceso denegado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Evento evento,
                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(eventoService.update(id, evento));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            eventoService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }
    
    @PostMapping("/{id}/artists")
    public ResponseEntity<?> addArtist(@PathVariable Long id,
                                       @RequestBody Map<String, Long> body,
                                       @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(eventoService.addArtist(id, body.get("artistId")));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }
    
    @DeleteMapping("/{id}/artists/{artistId}")
    public ResponseEntity<?> removeArtist(@PathVariable Long id,
                                          @PathVariable Long artistId,
                                          @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(eventoService.removeArtist(id, artistId));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }
    
    @PutMapping("/{id}/confirmed")
    public ResponseEntity<?> confirm(@PathVariable Long id,
                                     @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(eventoService.confirm(id));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }
    
    @PutMapping("/{id}/rescheduled")
    public ResponseEntity<?> reschedule(@PathVariable Long id,
                                        @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                        @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(eventoService.reschedule(id, startDate));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }
    
    @PutMapping("/{id}/canceled")
    public ResponseEntity<?> cancel(@PathVariable Long id,
                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(eventoService.cancel(id));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }
    
}
