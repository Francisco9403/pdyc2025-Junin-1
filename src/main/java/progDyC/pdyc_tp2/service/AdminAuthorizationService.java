package progDyC.pdyc_tp2.service;

import progDyC.pdyc_tp2.model.Admin;

public interface AdminAuthorizationService {

    Admin authorize(String token) throws Exception;
}
