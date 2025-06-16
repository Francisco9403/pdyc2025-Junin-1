package progDyC.pdyc_tp2.service;

import java.util.List;

import progDyC.pdyc_tp2.model.User;


public interface UserService {
    public List<User> getAll();
    public void create(User user) throws Exception;
    public void update(Long id, User user);
    public User getInstance(Long id);
    public void delete(Long id);

}
