package Services;

import Communication.ServerProxy;
import Models.MainModel;
import Models.User;
import Models.UserList;

public class RegisterService implements Service {
    private ServerProxy sp;
    private MainModel model;

    public RegisterService(){
        sp = ServerProxy.get();
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];
        String ipAddress = (String) obj[2];

        //create user model
        sp.register(username, password, ipAddress);
    }

    @Override
    public void doService(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];

        if(model.getUser().getName() == null){
            //add user to list of all user
            User newUser = new User(username, password);
            model.setUser(newUser);
        }

    }
}

