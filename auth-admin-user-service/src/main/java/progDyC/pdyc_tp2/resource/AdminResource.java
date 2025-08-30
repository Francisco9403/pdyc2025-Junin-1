package progDyC.pdyc_tp2.resource;

import java.util.HashMap;
//import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
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

import progDyC.pdyc_tp2.DTO.AdminAuthenticationRequestDTO;
import progDyC.pdyc_tp2.model.Admin;
import progDyC.pdyc_tp2.service.AdminAuthenticationServiceImp;
import progDyC.pdyc_tp2.service.AdminAuthorizationService;
import progDyC.pdyc_tp2.service.AdminService;

@RestController
@RequestMapping("/admins")
public class AdminResource {        //RECORDAR QUE AQUI SE MANEJAN LAS SOLICITUDES http Y SE RETORNAN LOS HTML
                                    //  LUEGO SE LE SOLICITA AL service QUE HACER
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminAuthenticationServiceImp authenticationService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AdminAuthorizationService authorizationService;

    @GetMapping
    public ResponseEntity<?> getAdmins(@RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            return ResponseEntity.ok(adminService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Admin admin,
                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            adminService.create(admin);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Admin admin,
                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            adminService.update(id, admin);
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
            return ResponseEntity.ok(adminService.getInstance(id));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,
                                    @RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            adminService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }


    @PostMapping(path = "/auth", produces = "application/json")     //Redirigir aqui cunado no pueda entrar a un endpoint privado
    public ResponseEntity<?> authentication(@RequestBody AdminAuthenticationRequestDTO dto) { //Se reciben el nombre y la contraseña del admin
        try {                                                                         //Al que se quiere autenticar, se validaran en la BD y de ser
            Admin admin = modelMapper.map(dto, Admin.class);          //Correctos se devolvera un token, el caul valida que ya esta autenticado
            String token = authenticationService.authenticate(admin);                 //Para poder usar los endpoints privados

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("token", token);

            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }


}
