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

import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.service.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoResource {

    @Autowired
    private EventoService service;

    @GetMapping
    public List<Evento> getEventos(){
        return service.getAll();
    }
    @PostMapping
    public void create(@RequestBody Evento evento){
        service.create(evento);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Evento evento){
        service.update(id,evento);
    }
    @GetMapping("/{id}")
    public Evento getInstance(@PathVariable Long id){
        return service.getInstance(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
