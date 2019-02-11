package Services;

import Command.ErrorCommand;
import Models.*;
import sun.applet.Main;
import sun.rmi.runtime.Log;

public class LoginService implements Service {
    MainModel model;

    public LoginService(){
        model = MainModel.get();
    }
    @Override
    public Object doService(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];

        //check that user exists with that username and password
        if(model.getUserList().findUser(username, password)){
            return true;
        } else{
            return new ErrorCommand("user does not exist");
        }

    }
}
