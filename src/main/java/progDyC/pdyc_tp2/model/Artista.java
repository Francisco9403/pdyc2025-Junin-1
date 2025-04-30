package progDyC.pdyc_tp2.model;

import jakarta.persistence.*;

@Entity
@Table(name="artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String nombre;
    public enum generoMusic{
        rock,
        techno,
        pop,
        jazz,
        folk
    }
    public Long getId() {
        return id;
    }
  
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
