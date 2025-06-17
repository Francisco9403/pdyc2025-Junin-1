package progDyC.pdyc_tp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import progDyC.pdyc_tp2.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String email);
    public User findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);
}

