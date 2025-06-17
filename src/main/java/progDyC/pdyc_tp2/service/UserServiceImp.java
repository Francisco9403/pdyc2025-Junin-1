package progDyC.pdyc_tp2.service;


//import java.net.PasswordAuthentication;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import progDyC.pdyc_tp2.DTO.UserAuthenticationRequestDTO;
import progDyC.pdyc_tp2.events.util.PasswordEncoderUtil;
import progDyC.pdyc_tp2.model.Artista;
import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.model.EventoState;
import progDyC.pdyc_tp2.model.User;
import progDyC.pdyc_tp2.repository.EventoRepository;
import progDyC.pdyc_tp2.repository.UserRepository;
import progDyC.pdyc_tp2.repository.ArtistaRepository;
@Service
public class UserServiceImp implements UserService{
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArtistaRepository artistaRepository;
    @Autowired
    private EventoRepository eventoRepository;

    private PasswordEncoderUtil passwordEncoder;


    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }
    @Override
    public void create(UserAuthenticationRequestDTO dto) throws Exception { //Para registrar un usuario
        if (userRepository.existsByNombre(dto.getNombre())) {
            throw new Exception("El nombre ya está en uso");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new Exception("El email ya está en uso");
        }

        User user = new User();
        user.setNombre(dto.getNombre());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);
    }

    @Override
    public void update(Long id, User user){
        User userBD = userRepository.findById(id).get();
        userBD.setNombre(user.getNombre());
        userRepository.save(userBD);
    }
    @Override
    public User getInstance(Long id){
        return userRepository.findById(id).get();
    }
    @Override
    public void delete(Long id){
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }
    @Override
    public User findByNombre(String nombre){
        return userRepository.findByNombre(nombre); //Ver si al fiinal no hay que agregar el ".get()"
    }


    @Override
    public void seguirArtista(Long userId, Long artistaId) {
        User userbd = userRepository.findById(userId).orElseThrow();
        Artista artistabd = artistaRepository.findById(artistaId).orElseThrow();
        userbd.getArtistasSeguidos().add(artistabd);
        userRepository.save(userbd);
    }

    @Override
    public void dejarSeguirArtista(Long userId, Long artistaId) {
        User userbd = userRepository.findById(userId).orElseThrow();
        Artista artistabd = artistaRepository.findById(artistaId).orElseThrow();
        userbd.getArtistasSeguidos().remove(artistabd);
        userRepository.save(userbd);
    }

    @Override
    public List<Artista> listaArtista(Long userId) {
        return userRepository.findById(userId)  //Obtengo el usuario de la BD
                .orElseThrow()                  //Sino lanzo una excepocion
                .getArtistasSeguidos()          //Obtengo su lista de artistas seguidos
                .stream().collect(Collectors.toList());
    }

    @Override
    public void seguirEvento(Long userId, Long eventoId) {
        User userbd = userRepository.findById(userId).orElseThrow();
        Evento eventobd = eventoRepository.findById(eventoId).filter(e -> !e.getState().equals(EventoState.TENTATIVE)) //flitro para asgurar que el evento sea tentativo
                .orElseThrow();
        userbd.getEventosFavoritos().add(eventobd);
        userRepository.save(userbd);
    }

    @Override
    public void dejarSeguirEvento(Long userId, Long eventoId) {
        User userbd = userRepository.findById(userId).orElseThrow(); //Habria que verificar primero si el evento obtenido de la BD
        Evento eventobd = eventoRepository.findById(eventoId).orElseThrow();//Pertenece a la lista de eventos favoritos del usuario
        userbd.getEventosFavoritos().remove(eventobd);
        userRepository.save(userbd);
    }

    @Override
    public List<Evento> listaEventoVigente(Long userId) {
        return userRepository.findById(userId)  //Falta filtrar por los que esten vigentes (ver a que se refiere con vigente)
                .orElseThrow()
                .getEventosFavoritos()
                .stream().collect(Collectors.toList());
    }

    @Override
    public List<Evento> listaEventoProximos(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        LocalDate now = LocalDate.now();
        return user.getArtistasSeguidos().stream()
                .flatMap(artist -> artist.getEvents().stream())  ///te trae los eventos de un artista y verifica que esten en tentativos y los muestra
                .filter(e -> e.getStartDate().isAfter(now) && !e.getState().equals(EventoState.TENTATIVE))
                .sorted((e1, e2) -> e1.getStartDate().compareTo(e2.getStartDate())) //ver si cumple el mostrar las fechas mas proximas
                .collect(Collectors.toList());
    }
}
