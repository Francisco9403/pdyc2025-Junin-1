package progDyC.pdyc_tp2.service;
    
import progDyC.pdyc_tp2.model.User;

public interface UserAuthenticationService {

    String authenticate(User user) throws Exception;
}
