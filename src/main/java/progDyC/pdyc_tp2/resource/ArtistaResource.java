package progDyC.pdyc_tp2.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import progDyC.pdyc_tp2.model.Artista;
import progDyC.pdyc_tp2.service.ArtistaService;

@RestController
@RequestMapping("/artista")
public class ArtistaResource {

    @Autowired
    private ArtistaService service;
    
    @GetMapping
    public List<Artista> getArtistas(){
        return service.getAll();
    }
    @PostMapping
    public void create(@RequestBody Artista artista){
        service.create(artista);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Artista artista){
        service.update(id,artista);
    }
    @GetMapping("/{id}")
    public Artista getInstance(@PathVariable Long id){
        return service.getInstance(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
