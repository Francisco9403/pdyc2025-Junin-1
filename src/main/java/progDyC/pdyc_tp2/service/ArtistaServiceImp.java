package progDyC.pdyc_tp2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import progDyC.pdyc_tp2.model.Artista;
import progDyC.pdyc_tp2.model.Genero;
import progDyC.pdyc_tp2.repository.ArtistaRepository;

@Service
public class ArtistaServiceImp implements ArtistaService {

    @Autowired
    private ArtistaRepository repository;

    @Override
    public List<Artista> getAll(Genero genero) {
        if (genero != null) {
            return repository.findByGenero(genero);
        }
        return repository.findAll();
    }

    @Override
    public Artista getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Artista create(Artista artista) {
        artista.setActive(true);
        return repository.save(artista);
    }

    @Override
    public Artista update(Long id, Artista artista) {
        Artista existing = repository.findById(id).get();
        if (!existing.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Artista desactivado no editable");
        }
        existing.setNombre(artista.getNombre());
        existing.setGenero(artista.getGenero());
        return repository.save(existing);
    }

    @Override
    public void deleteOrDeactivate(Long id) {
        Artista existing = repository.findById(id).get();
        if (existing.getEvents().isEmpty()) {
            repository.delete(existing);
        } else {
            existing.setActive(false);
            repository.save(existing);
        }
    }
}