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
        if (!jwtTokenUtil.verify(token)) {
            throw new Exception("Token inválido.");
        }
        String username = jwtTokenUtil.getSubject(token);
        Admin admin = adminService.findByUsername(username);
        if (admin == null) {
            throw new Exception("Admin no encontrado.");
        }
        return admin;
    }
}