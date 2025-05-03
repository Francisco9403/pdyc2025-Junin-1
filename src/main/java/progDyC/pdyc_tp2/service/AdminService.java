package progDyC.pdyc_tp2.service;

import java.util.List;

import progDyC.pdyc_tp2.model.Admin;

public interface AdminService {
    List<Admin> getAll();
    void create(Admin admin);
    void update(Long id,Admin admin);
    Admin getInstance(Long id);
    void delete(Long id);

}
