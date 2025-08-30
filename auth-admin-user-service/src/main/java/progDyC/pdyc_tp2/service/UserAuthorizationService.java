package progDyC.pdyc_tp2.service;

import progDyC.pdyc_tp2.model.User;

public interface UserAuthorizationService {

     User authorize(String token) throws Exception;
}
