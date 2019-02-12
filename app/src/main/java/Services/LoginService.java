package Services;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import Communication.CommandManager;
import Communication.ServerProxy;
import Communication.SocketConnectionError;
import Communication.SocketInitializer;
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

        if(CommandManager.get() == null) {
            SocketInitializer si = new SocketInitializer();
            int port = 8080;
            si.execute(ipAddress, port);
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep((long) .002);
                    if (CommandManager.get() != null) {
                        break;
                    }
                } catch (InterruptedException e) {
                    System.out.println("Error sleeping");
                }
            }
        }
        ServerProxy sp = new ServerProxy();
        //send loginCommand
        sp.login(username, password, ipAddress);
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
                user.setLoggedIn(true);
                //set gamelist
                model.setGameList(gameList);
            }
        } catch(UnknownHostException e){
            model.setErrorMessage("Couldn't get localhost for some reason...");
        }

    }
}
