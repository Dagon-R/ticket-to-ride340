package Services;

import java.util.concurrent.TimeUnit;

import Communication.CommandManager;
import Communication.ServerProxy;
import Communication.SocketInitializer;
import Models.*;

public class LoginService implements Service {
    private MainModel model;
    private SocketInitializer si = new SocketInitializer();

    public LoginService(){
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];
        String ipAddress = (String) obj[2];

        if(CommandManager.get() == null) {
            int port = 8080;
            si.resetFinished();
            si.execute(ipAddress, port);
            while (si.isFinished() == 0) {
                try {
                    TimeUnit.SECONDS.sleep((long) .002);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (si.isFinished() == -1) {MainModel.get().setErrorMessage("Failed Connection"); return;}
        String authToken = username + Long.toString(System.currentTimeMillis());
        model.setAuthToken(authToken);

        //send loginCommand
        ServerProxy.get().login(username, password, ipAddress, authToken);
    }

    @Override
    public void doService(Object... obj) {
        //Check params
        if(obj.length != 5){
            model.setErrorMessage("Error Logging in");
            System.out.println("ERROR: " + obj.length + " instead of 5 params on frontend login service");
        }

        String username = (String) obj[0];
        String password = (String) obj[1];
        String ipAddress = (String) obj[2];
        ClientGameList gameList = (ClientGameList) obj[3];
        String authToken = (String) obj[4];

        if(model.getAuthToken().equals(authToken)){
            model.setIPAddress(ipAddress);
            //set gamelist
            model.setGameList(gameList);
            //set user
            model.getUser().setName(username);
            model.getUser().setPassword(password);
            model.getUser().setLoggedIn(true);
        }

    }
}
