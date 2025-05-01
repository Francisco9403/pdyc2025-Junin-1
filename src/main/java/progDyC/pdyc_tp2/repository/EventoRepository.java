package progDyC.pdyc_tp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.model.EventoState;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long>{
    List<Evento> findByState(EventoState state);

}
