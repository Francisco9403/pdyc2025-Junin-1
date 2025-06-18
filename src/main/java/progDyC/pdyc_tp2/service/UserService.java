package progDyC.pdyc_tp2.service;

import java.util.List;

import progDyC.pdyc_tp2.DTO.UserAuthenticationRequestDTO;
import progDyC.pdyc_tp2.model.Artista;
import progDyC.pdyc_tp2.model.Evento;
import progDyC.pdyc_tp2.model.User;


public interface UserService {
    public List<User> getAll();
    public void create(UserAuthenticationRequestDTO userDTO) throws Exception;
    public void update(Long id, User user);
    public User getInstance(Long id);
    public void delete(Long id);
    public User findByNombre(String nombre);
    public void seguirArtista(Long userId, Long artistaId);
    public void dejarSeguirArtista(Long userId, Long artistaId);
    public List<Artista> listaArtista(Long userId);
    public void seguirEvento(Long userId, Long eventoId);
    public void dejarSeguirEvento(Long userId, Long eventoId);
    public List<Evento> listaEventoVigente(Long userId);
    public List<Evento> listaEventoProximosDeMisArtistas(Long userId);

}
