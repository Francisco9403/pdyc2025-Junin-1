package progDyC.pdyc_tp2.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import progDyC.pdyc_tp2.model.Artista;
import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.model.User;
import progDyC.pdyc_tp2.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {    //VER: Creo que en este resource al ser "restringido" debo "autorizar" meidante el 
                              // Token obtenido en PublicResource (token perteneciente a un usuario), en cada metodo, que el usuario
    @Autowired                      //cuente con dicho token como hicimos en AdminResource
    private UserService userService;
    /*@Autowired
    private UserAuthenticationService userAuthenticationService;
    @Autowired
    private ModelMapper modelMapper;*/

    @GetMapping
    public List<User> getUsers(){
        return userService.getAll();
    }
    /*-------------------PASO TODO ESTO A PublicResource PARA QUE SEA ACCEDIDO SIN NECESIDAD DE AUTENTICAR-------------------
    @PostMapping
    public void registrarUsuario(@RequestBody UserAuthenticationRequestDTO userDTO) throws Exception{ //Registro un usuario (guardandolo en la BD)
        userService.create(userDTO);            //Estos metodos hay que ver donde ubicarlos, porque si "/users" es restringido
    }                                       //no se van a poder utilizar

    @PostMapping(path = "/auth", produces = "application/json")
    public ResponseEntity<?> authentication(@RequestBody UserAuthenticationRequestDTO userDTO) { //AUTENTICO a un usuario (verfico que exista en la BD)
        try {
            User user = modelMapper.map(userDTO, User.class);
            String token = userAuthenticationService.authenticate(user);

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("token", token);                           //De existir en la BD, dfevuelvo un token

            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }-------------------HASTA ACA LO QUE PASO-------------------*/
    /*@PostMapping
    public void create(@RequestBody User user){
        service.create(user);
    }*/
    @PostMapping("/{id}")
    public void autenticarUsuario(){

    }
    
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody User user){
        userService.update(id,user);
    }
    @GetMapping("/{id}")
    public User getInstance(@PathVariable Long id){
        return userService.getInstance(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }


    @PostMapping("/{id}/seguidos/{artistId}")
    public void Seguir(@PathVariable Long userId, @PathVariable Long artistId) {
        userService.seguirArtista(userId, artistId);
    }

    @DeleteMapping("/{id}/seguidos/{artistId}")
    public void DejarSeguir(@PathVariable Long userId, @PathVariable Long artistId) {
        userService.dejarSeguirArtista(userId, artistId);
    }

    @GetMapping("/{id}/seguidos-artistas")
    public List<Artista> ListarSeguidos(@PathVariable Long userId) {
        return userService.listaArtista(userId);
    }

    @PostMapping("/{id}/favoritos/{eventId}")
    public void AgregarEventoFavorito(@PathVariable Long userId, @PathVariable Long eventId) {
        userService.seguirEvento(userId, eventId);
    }

    @DeleteMapping("/{id}/favoritos/{eventId}")
    public void EliminarEventoFavorito(@PathVariable Long userId, @PathVariable Long eventId) {
        userService.dejarSeguirEvento(userId, eventId);
    }

    @GetMapping("/{id}/favoritos")
    public List<Evento> listarFavoritos(@PathVariable Long userId) {
        return userService.listaEventoVigente(userId);
    }

    @GetMapping("/{id}/eventos-favoritos")
    public List<Evento> listarFavoritosProximos(@PathVariable Long userId) {
        return userService.listaEventoProximos(userId);
    }




}
