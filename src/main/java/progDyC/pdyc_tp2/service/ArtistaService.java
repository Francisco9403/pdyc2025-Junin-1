package progDyC.pdyc_tp2.service;

import java.util.List;

import progDyC.pdyc_tp2.model.Artista;

public interface ArtistaService {
    public List<Artista> getAll();
    public void create(Artista artista);
    public void update(Long id,Artista artista);
    public Artista getInstance(Long id);
    public void delete(Long id);

}
