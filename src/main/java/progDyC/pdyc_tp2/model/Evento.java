package progDyC.pdyc_tp2.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;
import java.util.HashSet;

@Entity
@Table(name="eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventoState state = EventoState.TENTATIVE;

    @ManyToMany                                       //Relacion n-n, Lo siguiente solo se hace desde el lado dueño de la relacion
    @JoinTable(                                       //Crea una nueva tabla intermedia entre Eventos y Artista
            name = "event_artists",                   //Nombre de la tabla
            joinColumns = @JoinColumn(name = "event_id"),       //Estas dos son las columnas que unen ambas entidades
            inverseJoinColumns = @JoinColumn(name = "artist_id") 
    )
    private Set<Artista> artists = new HashSet<>();   //Estructura de datos que evita duplicados automaticamente, tambien posee una busque mas rapida (internamente usa tabla hash)

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