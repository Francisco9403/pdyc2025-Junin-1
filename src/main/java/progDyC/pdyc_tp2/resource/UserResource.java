package progDyC.pdyc_tp2.resource;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import progDyC.pdyc_tp2.DTO.CreateUserRequestDTO;
import progDyC.pdyc_tp2.model.Artista;
import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.model.User;
import progDyC.pdyc_tp2.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getUsers(){
        return service.getAll();
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateUserRequestDTO userDTO){
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO, User.class);
        try {
            service.create(user);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
    /*@PostMapping
    public void create(@RequestBody User user){
        service.create(user);
    }*/
    
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody User user){
        service.update(id,user);
    }
    @GetMapping("/{id}")
    public User getInstance(@PathVariable Long id){
        return service.getInstance(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }


    @PostMapping("/{id}/seguidos/{artistId}")
    public void Seguir(@PathVariable Long userId, @PathVariable Long artistId) {
        service.seguirArtista(userId, artistId);
    }

    @DeleteMapping("/{id}/seguidos/{artistId}")
    public void DejarSeguir(@PathVariable Long userId, @PathVariable Long artistId) {
        service.dejarSeguirArtista(userId, artistId);
    }

    @GetMapping("/{id}/seguidos-artistas")
    public List<Artista> ListarSeguidos(@PathVariable Long userId) {
        return service.listaArtista(userId);
    }

    @PostMapping("/{id}/favoritos/{eventId}")
    public void AgregarEventoFavorito(@PathVariable Long userId, @PathVariable Long eventId) {
        service.seguirEvento(userId, eventId);
    }

    @DeleteMapping("/{id}/favoritos/{eventId}")
    public void EliminarEventoFavorito(@PathVariable Long userId, @PathVariable Long eventId) {
        service.dejarSeguirEvento(userId, eventId);
    }

    @GetMapping("/{id}/favoritos")
    public List<Evento> listarFavoritos(@PathVariable Long userId) {
        return service.listaEventoVigente(userId);
    }

    @GetMapping("/{id}/eventos-favoritos")
    public List<Evento> listarFavoritosProximos(@PathVariable Long userId) {
        return service.listaEventoProximos(userId);
    }




}
