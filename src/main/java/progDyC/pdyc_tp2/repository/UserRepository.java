package progDyC.pdyc_tp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import progDyC.pdyc_tp2.model.Artista;
import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.model.User;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String email);
    public User findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);
    List<User> findDistinctByArtistasSeguidosIn(Set<Artista> artistas);
    List<User> findDistinctByEventosFavoritosContaining(Evento evento);
}

