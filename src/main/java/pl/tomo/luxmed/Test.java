package pl.tomo.luxmed;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Test implements InitializingBean {


    @Autowired private Login login;

    @Override
    public void afterPropertiesSet() throws Exception {

        //login.login();

    }
}
