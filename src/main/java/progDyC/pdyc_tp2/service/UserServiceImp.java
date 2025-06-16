package progDyC.pdyc_tp2.service;


//import java.net.PasswordAuthentication;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import progDyC.pdyc_tp2.events.util.PasswordEncoderUtil;
import progDyC.pdyc_tp2.model.User;
import progDyC.pdyc_tp2.repository.UserRepository;
@Service
public class UserServiceImp implements UserService{
    
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoderUtil passwordEncoder;


    @Override
    public List<User> getAll(){
        return repository.findAll();
    }
    @Override
    public void create(User user) throws Exception{
        User userDB = repository.findByNombre(user.getNombre());
        if(userDB != null){
            throw new Exception();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
    @Override
    public void update(Long id, User user){
        User userBD = repository.findById(id).get();
        userBD.setNombre(user.getNombre());        
        repository.save(userBD);
    }
    @Override
    public User getInstance(Long id){
        return repository.findById(id).get();
    }
    @Override
    public void delete(Long id){
        User user = repository.findById(id).get();
        repository.delete(user);
    }

}
