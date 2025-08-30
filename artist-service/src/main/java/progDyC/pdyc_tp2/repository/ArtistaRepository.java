package progDyC.pdyc_tp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import progDyC.pdyc_tp2.model.Artista;
import progDyC.pdyc_tp2.model.Genero;

import java.util.List;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    List<Artista> findByGenero(Genero genero);
}