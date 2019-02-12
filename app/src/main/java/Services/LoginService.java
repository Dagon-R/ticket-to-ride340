package Services;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
            model.setErrorMessage("Error connecting to socket");
        }
    }

    @Override
    public void doService(Object... obj) {
        //Check params
        if(obj.length != 4){
            model.setErrorMessage("Error Logging in");
            System.out.println("ERROR: " + obj.length + " instead of 4 params on frontend login service");
        }
        assert obj[0] instanceof String;
        assert obj[1] instanceof String;
        assert obj[2] instanceof String;
        assert obj[3] instanceof ClientGameList;

        String username = (String) obj[0];
        String password = (String) obj[1];
        String ipAddress = (String) obj[2];
        ClientGameList gameList = (ClientGameList) obj[3];

        try{
            String localhost = InetAddress.getLocalHost().getHostAddress();

            if(localhost.equals(ipAddress)){
                model.setIPAddress(localhost);
                //set user
                User user = new User(username,password);
                model.setUser(user);
                //set gamelist
                model.setGameList(gameList);
            }
        } catch(UnknownHostException e){
            model.setErrorMessage("Couldn't get localhost for some reason...");
        }

    }
}
