package progDyC.pdyc_tp2.service;

import progDyC.pdyc_tp2.model.Admin;

public interface AdminAuthenticathionService {

    String authenticate(Admin admin) throws Exception;
}
