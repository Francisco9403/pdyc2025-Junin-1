package progDyC.pdyc_tp2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import progDyC.pdyc_tp2.events.util.JwtTokenUtil;
import progDyC.pdyc_tp2.model.User;

@Service
public class UserAuthorizationServiceImp implements UserAuthorizationService{

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserService userService;

    @Override
    public User authorize(String token) throws Exception{
        if (!jwtTokenUtil.verify(token)){   //verifica que el token recibido sea valido (se llama a la clase jwtTokenUtil)
            throw new Exception("Token inválido.");
        }
        String username = jwtTokenUtil.getSubject(token);//De ser valido extraigo el subject (nombre del admin) del token
        User user = userService.findByNombre(username);//Uso el nombre extraido para recuperar su instancia de la BD
        if (user == null) {
            throw new Exception("Admin no encontrado.");//Si no se encontro lanza exception
        }
        return user; //Si el token es valido retorno la intancia del admin al que pertenece
    }  
}
