package progDyC.pdyc_tp2.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "nombre"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "artistas_seguidos",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "artistas_id")
    )
    private Set<Artista> ArtistasSeguidos = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "eventos_favoritos",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "artistas_id")
    )
    private Set<Evento> EventosFavoritos = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Artista> getArtistasSeguidos() {
        return ArtistasSeguidos;
    }

    public void setArtistasSeguidos(Set<Artista> artistasSeguidos) {
        ArtistasSeguidos = artistasSeguidos;
    }

    public Set<Evento> getEventosFavoritos() {
        return EventosFavoritos;
    }

    public void setEventosFavoritos(Set<Evento> eventosFavoritos) {
        EventosFavoritos = eventosFavoritos;
    }
}
