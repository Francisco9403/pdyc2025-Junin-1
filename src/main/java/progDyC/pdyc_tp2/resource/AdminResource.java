package progDyC.pdyc_tp2.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import progDyC.pdyc_tp2.model.Admin;
import progDyC.pdyc_tp2.service.AdminService;

@RestController
@RequestMapping("/admins")
public class AdminResource {        //RECORDAR QUE AQUI SE MANEJAN LAS SOLICITUDES http Y SE RETORNAN LOS HTML
                                    //  LUEGO SE LE SOLICITA AL service QUE HACER
    @Autowired
    private AdminService service;

    @GetMapping
    public List<Admin> getAdmins(){
        return service.getAll();
    }
    @PostMapping
    public void create(@RequestBody Admin admin){
        service.create(admin);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Admin admin){
        service.update(id,admin);
    }
    @GetMapping("/{id}")
    public Admin getInstance(@PathVariable Long id){
        return service.getInstance(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
