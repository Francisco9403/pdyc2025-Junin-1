package progDyC.pdyc_tp2.service;

import progDyC.pdyc_tp2.model.Admin;

public interface AdminAuthorizationService {

    Admin authorize(String token) throws Exception; //Preguntar, porque en el tp el metodo devuelve User
                                                    //public User authorize(String token) throws Exception
}
