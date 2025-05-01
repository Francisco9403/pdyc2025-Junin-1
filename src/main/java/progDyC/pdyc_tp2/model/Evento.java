package progDyC.pdyc_tp2.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;
import java.util.HashSet;

@Entity
@Table(name="eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private EventoState state = EventoState.TENTATIVE;

    @ManyToMany
    @JoinTable(
            name = "event_artists",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<Artista> artists = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public EventoState getState() {
        return state;
    }

    public void setState(EventoState state) {
        this.state = state;
    }

    public Set<Artista> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artista> artists) {
        this.artists = artists;
    }
}