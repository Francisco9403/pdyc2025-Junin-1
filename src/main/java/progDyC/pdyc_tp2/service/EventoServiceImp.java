package progDyC.pdyc_tp2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.repository.EventoRepository;

public class EventoServiceImp implements EventoService{

    @Autowired
    private EventoRepository repository;

    @Override
    public List<Evento> getAll(){
        return repository.findAll();
    }
    @Override
    public void create(Evento evento){
        repository.save(evento);
    }
    @Override
    public void update(Long id, Evento evento){
        Evento eventoBD = repository.findById(id).get();
        eventoBD.setNombre(evento.getNombre());
        eventoBD.setDescripcion(evento.getDescripcion());
        eventoBD.setFechaFinalizacion(evento.getFechaFinalizacion());
        eventoBD.setEstado(evento.getEstado());
        repository.save(eventoBD);
    }
    @Override
    public Evento getInstance(Long id){
        return repository.findById(id).get();
    }
    @Override
    public void delete(Long id){
        Evento evento = repository.findById(id).get();
        repository.delete(evento);
    }

}
