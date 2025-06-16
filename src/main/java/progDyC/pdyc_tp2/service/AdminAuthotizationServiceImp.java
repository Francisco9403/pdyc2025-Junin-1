package progDyC.pdyc_tp2.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import progDyC.pdyc_tp2.events.util.JwtTokenUtil;
import progDyC.pdyc_tp2.model.Admin;
@Service

public class AdminAuthotizationServiceImp implements AdminAuthorizationService{

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AdminService adminService;

    @Override
    public Admin authorize(String token) throws Exception {
        if (!jwtTokenUtil.verify(token)){   //verifica que el token recibido sea valido (se llama a la clase jwtTokenUtil)
            throw new Exception("Token inválido.");
        }
        String username = jwtTokenUtil.getSubject(token);//De ser valido extraigo el subject (nombre del admin) del token
        Admin admin = adminService.findByUsername(username);//Uso el nombre extraido para recuperar su instancia de la BD
        if (admin == null) {
            throw new Exception("Admin no encontrado.");//Si no se encontro lanza exception
        }
        return admin; //Si el token es valido retorno la intancia del admin al que pertenece
    }
}