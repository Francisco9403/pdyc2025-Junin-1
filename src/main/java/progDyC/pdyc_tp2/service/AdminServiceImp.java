package progDyC.pdyc_tp2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import progDyC.pdyc_tp2.model.Admin;
import progDyC.pdyc_tp2.repository.AdminRepository;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminRepository repository;

    @Override
    public List<Admin> getAll(){
        return repository.findAll();
    }
    @Override
    public void create(Admin admin){
        repository.save(admin);
    } 
    @Override
    public void update(Long id, Admin admin){
        Admin adminBD =repository.findById(id).get();
        adminBD.setNombre(admin.getNombre());
        repository.save(adminBD);
    }
    @Override
    public Admin getInstance(Long id){
        return repository.findById(id).get();
    }
    @Override
    public void delete(Long id){
        Admin admin = repository.findById(id).get();
        repository.delete(admin);
    }

}
