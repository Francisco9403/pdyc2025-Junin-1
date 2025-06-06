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
        Admin existAdmin = adminService.findByUsername(admin.getNombre());
        if (existAdmin == null) {
            throw new Exception("Admin no encontrado.");
        }
        if (!passwordEncoder.verify(admin.getPassword(), existAdmin.getPassword())) {
            throw new Exception("Contraseña incorrecta.");
        }
        return jwtTokenUtil.generateToken(existAdmin.getNombre());
    }
}