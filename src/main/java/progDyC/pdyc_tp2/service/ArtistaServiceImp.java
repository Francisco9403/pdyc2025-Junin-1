package progDyC.pdyc_tp2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import progDyC.pdyc_tp2.model.Artista;
import progDyC.pdyc_tp2.repository.ArtistaRepository;

public class ArtistaServiceImp implements ArtistaService{

    @Autowired
    private ArtistaRepository repository;

    @Override
    public List<Artista> getAll(){
        return repository.findAll();
    }
    @Override
    public void create(Artista artista){
        repository.save(artista);
    } 
    @Override
    public void update(Long id, Artista artista){
        Artista artistaBD =repository.findById(id).get();
        artistaBD.setNombre(artista.getNombre());
        repository.save(artistaBD);
    }
    @Override
    public Artista getInstance(Long id){
        return repository.findById(id).get();
    }
    @Override
    public void delete(Long id){
        Artista artista = repository.findById(id).get();
        repository.delete(artista);
    }

}
