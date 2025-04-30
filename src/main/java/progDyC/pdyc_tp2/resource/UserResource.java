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

import progDyC.pdyc_tp2.model.User;
import progDyC.pdyc_tp2.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getUsers(){
        return service.getAll();
    }
    @PostMapping
    public void create(@RequestBody User user){
        service.create(user);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody User user){
        service.update(id,user);
    }
    @GetMapping("/{id}")
    public User getInstance(@PathVariable Long id){
        return service.getInstance(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
