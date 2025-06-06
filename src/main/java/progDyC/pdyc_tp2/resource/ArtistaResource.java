package progDyC.pdyc_tp2.resource;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import progDyC.pdyc_tp2.model.Artista;
import progDyC.pdyc_tp2.model.Genero;
import progDyC.pdyc_tp2.service.AdminAuthorizationService;
import progDyC.pdyc_tp2.service.ArtistaService;

@RestController
@RequestMapping("/admin/artists")
public class ArtistaResource {

    @Autowired
    private ArtistaService service;

    @Autowired
    private AdminAuthorizationService authorizationService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) Genero genero,
                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(service.getAll(genero));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id,
                                     @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Artista artista,
                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(service.create(artista));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    } 

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Artista artista,
                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(service.update(id, artista));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            service.deleteOrDeactivate(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

}