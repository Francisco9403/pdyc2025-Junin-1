package progDyC.pdyc_tp2.service;

import java.util.List;

import progDyC.pdyc_tp2.model.Admin;

public interface AdminService {
    public List<Admin> getAll();
    public void create(Admin admin);
    public void update(Long id,Admin admin);
    public Admin getInstance(Long id);
    public void delete(Long id);

}
