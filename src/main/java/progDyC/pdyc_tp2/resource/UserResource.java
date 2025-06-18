package progDyC.pdyc_tp2.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.model.User;
import progDyC.pdyc_tp2.service.UserAuthorizationService;
import progDyC.pdyc_tp2.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {    //VER: Creo que en este resource al ser "restringido" debo "autorizar" meidante el 
                              // Token obtenido en PublicResource (token perteneciente a un usuario), en cada metodo, que el usuario
    @Autowired                      //cuente con dicho token como hicimos en AdminResource
    private UserService userService;
    @Autowired
    private UserAuthorizationService authorizationService;

    @GetMapping
    public ResponseEntity<?> getUsers(@RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(userService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
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
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody User user,
                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            userService.update(id, user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getInstance(@PathVariable Long id,
                                         @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(userService.getInstance(id)); //Cuando tengo que devolver algo (como en una llamada @GET) la
        } catch (Exception e) {                                    //Respuestas la pongo dentro del ok()
            return ResponseEntity.status(403).build();
        }
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }


    @PostMapping("/{id}/seguidos/{artistId}")
    public ResponseEntity<?> seguir(@PathVariable Long userId,
                                    @PathVariable Long artistId,
                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            userService.seguirArtista(userId, artistId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("/{id}/seguidos/{artistId}")
    public ResponseEntity<?> dejarSeguir(@PathVariable Long userId,
                                         @PathVariable Long artistId,
                                         @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            userService.dejarSeguirArtista(userId, artistId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

     @GetMapping("/{id}/seguidos-artistas")
    public ResponseEntity<?> listarSeguidos(@PathVariable Long userId,
                                            @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(userService.listaArtista(userId));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping("/{id}/favoritos/{eventId}")
    public ResponseEntity<?> agregarEventoFavorito(@PathVariable Long userId,
                                                   @PathVariable Long eventId,
                                                   @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            userService.seguirEvento(userId, eventId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }


    @DeleteMapping("/{id}/favoritos/{eventId}")
    public ResponseEntity<?> eliminarEventoFavorito(@PathVariable Long userId,
                                                    @PathVariable Long eventId,
                                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            userService.dejarSeguirEvento(userId, eventId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }


    @GetMapping("/{id}/favoritos")
    public ResponseEntity<?> listarFavoritos(@PathVariable Long userId,
                                             @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(userService.listaEventoVigente(userId));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping("/{id}/eventos-favoritos")
    public ResponseEntity<?> listarFavoritosProximos(@PathVariable Long userId,
                                                     @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(userService.listaEventoProximosDeMisArtistas(userId));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }




}
