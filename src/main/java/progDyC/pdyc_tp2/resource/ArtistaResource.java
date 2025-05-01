package progDyC.pdyc_tp2.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import progDyC.pdyc_tp2.model.Artista;
import progDyC.pdyc_tp2.model.Genero;
import progDyC.pdyc_tp2.service.ArtistaService;

@RestController
@RequestMapping("/admin/artists")
public class ArtistaResource {

    @Autowired
    private ArtistaService service;

    @GetMapping
    public List<Artista> getAll(@RequestParam(required = false) Genero genero) {
        return service.getAll(genero);
    }

    @GetMapping("/{id}")
    public Artista getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Artista create(@RequestBody Artista artista) {
        return service.create(artista);
    }

    @PutMapping("/{id}")
    public Artista update(@PathVariable Long id, @RequestBody Artista artista) {
        return service.update(id, artista);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteOrDeactivate(id);
    }
}