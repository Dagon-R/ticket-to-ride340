package Services;

import Communication.ServerProxy;
import Models.*;

public class LoginService implements Service {
    private ServerProxy sp;
    private MainModel model;

    public LoginService(){
        sp = ServerProxy.get();
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];

        //send loginCommand
        sp.login(username, password);
    }

    @Override
    public void doService(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];


        if(model.getUser().getName() == null){
            //add user to list of all user
            User user = new User(username,password);
            model.setUser(user);
        }

    }
}
