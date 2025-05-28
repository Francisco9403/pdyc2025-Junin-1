package progDyC.pdyc_tp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import progDyC.pdyc_tp2.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findByUsername(@Param("username") String username);
}
