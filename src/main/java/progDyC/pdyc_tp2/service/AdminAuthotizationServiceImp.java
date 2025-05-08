package progDyC.pdyc_tp2.service;

import org.springframework.beans.factory.annotation.Autowired;

import progDyC.pdyc_tp2.events.util.JwtTokenUtil;
import progDyC.pdyc_tp2.model.Admin;

public class AdminAuthotizationServiceImp implements AdminAuthorizationService{

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AdminService adminService;

    @Override
    public Admin authorize(String token) throws Exception {
        // Verificar si el token es válido
        if (!jwtTokenUtil.verify(token)) {
            throw new Exception("Token inválido.");
        }

        // Obtener el subject (username)
        String username = jwtTokenUtil.getSubject(token);

        // Buscar el Admin por username
        Admin admin = adminService.findByUsername(username);
        if (admin == null) {
            throw new Exception("Admin no encontrado.");
        }

        return admin;
    }

}
