package progDyC.pdyc_tp2.service;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import progDyC.pdyc_tp2.events.util.JwtTokenUtil;
import progDyC.pdyc_tp2.events.util.PasswordEncoderUtil;
import progDyC.pdyc_tp2.model.Admin;
//import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AdminAuthenticationServiceImp implements AdminAuthenticathionService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PasswordEncoderUtil passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String authenticate(Admin admin) throws Exception {
        Admin existAdmin = adminService.findByUsername(admin.getNombre()); //Busco un admin de la BD por el nombre
        if (existAdmin == null) {
            throw new Exception("Admin no encontrado.");
        }
        if (!passwordEncoder.verify(admin.getPassword(), existAdmin.getPassword())) {//Verfico la contraseña recibida, con la de la BD
            throw new Exception("Contraseña incorrecta.");
        }
        return jwtTokenUtil.generateToken(existAdmin.getNombre());//Si todo es valido, el usuario esta autenticado (existe en la BD y su contraseña es correcta)
    }                                                             //Entonces se retorna un token que tendra como "subject" el "nombre" del admin autenticado  
}