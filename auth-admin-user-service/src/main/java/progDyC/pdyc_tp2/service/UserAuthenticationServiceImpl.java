package progDyC.pdyc_tp2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import progDyC.pdyc_tp2.events.util.PasswordEncoderUtil;
import progDyC.pdyc_tp2.model.User;
import progDyC.pdyc_tp2.events.util.JwtTokenUtil;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoderUtil passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;   

    @Override
    public String authenticate(User user) throws Exception{
        User userBD = userService.findByNombre(user.getNombre());//Busco un usuario de la BD por el nombre
        if (userBD == null){                //Recomendable buscar por id
            throw new Exception("El usuario: "+user.getNombre()+" no se encuentra registrado");
        }
        if (!passwordEncoder.verify(user.getPassword(), userBD.getPassword())){//Verfico la contraseña recibida, con la de la BD
            throw new Exception("Contraseña incorrecta");
        }
        return jwtTokenUtil.generateToken(userBD.getNombre());//Si todo es valido, el usuario esta autenticado (existe en la BD y su contraseña es correcta)
    }                                                         //Entonces se retorna un token que tendra como "subject" el "nombre" del admin autenticado  

}
