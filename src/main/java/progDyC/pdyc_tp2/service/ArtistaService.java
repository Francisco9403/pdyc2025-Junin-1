package progDyC.pdyc_tp2.service;

import java.util.List;

import progDyC.pdyc_tp2.model.Artista;
import progDyC.pdyc_tp2.model.Genero;

public interface ArtistaService {
    List<Artista> getAll(Genero genero);
    Artista getById(Long id);
    Artista create(Artista artista);
    Artista update(Long id, Artista artista);
    void deleteOrDeactivate(Long id);
}
