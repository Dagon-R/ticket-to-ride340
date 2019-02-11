package Services;

import Communication.ServerProxy;
import Communication.SocketConnectionError;
import Models.*;

public class LoginService implements Service {
    private ServerProxy sp;
    private MainModel model;

    public LoginService(){
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];
        String ipAddress = (String) obj[2];

        //send loginCommand
        try{
            sp = ServerProxy.create(ipAddress);
            sp.login(username, password, ipAddress);

        } catch(SocketConnectionError e){
            String message = e.getMessage();
            model.setErrorMessage(message);
        }
    }

    @Override
    public void doService(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];
        String ipAddress = (String) obj[2];
        ClientGameList gameList = (ClientGameList) obj[3];

        if(model.getUser().getName() == null){
            //add user to list of all user
            User user = new User(username,password);
            model.setUser(user);
        }

    }
}
