package progDyC.pdyc_tp2.service;

import java.util.List;

import progDyC.pdyc_tp2.model.Evento;

public interface EventoService {
    public List<Evento> getAll();
    public void create(Evento evento);
    public void update(Long id,Evento evento);
    public Evento getInstance(Long id);
    public void delete(Long id);

}
