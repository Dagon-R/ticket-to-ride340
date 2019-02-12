package Services;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import Communication.CommandManager;
import Communication.ServerProxy;
import Communication.SocketConnectionError;
import Communication.SocketInitializer;
import Models.ClientGameList;
import Models.MainModel;
import Models.User;
import Models.UserList;

public class RegisterService implements Service {
    private ServerProxy sp;
    private MainModel model;

    public RegisterService(){
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];
        String ipAddress = (String) obj[2];

        if(ServerProxy.get() == null) {
            SocketInitializer si = new SocketInitializer();
            int port = 8080;
            si.execute(ipAddress, port);
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep((long) .002);
                    sp = ServerProxy.get();
                    if (sp != null) {
                        break;
                    }
                } catch (InterruptedException e) {
                    System.out.println("Error sleeping");
                }
            }
        }
        String now = Long.toString(System.currentTimeMillis());
        String authToken = username + now;
        sp.register(username, password, ipAddress);//, authToken);
    }

    @Override
    public void doService(Object... obj) {
        //Check params
        if(obj.length != 4){
            model.setErrorMessage("Error Registering");
            System.out.println("ERROR: " + obj.length + " instead of 4 params on frontend register service");
        }

        String username = (String) obj[0];
        String password = (String) obj[1];
        String ipAddress = (String) obj[2];
        ClientGameList gameList = (ClientGameList) obj[3];

        if(CommandManager.get().getOwnIP().equals(ipAddress)){
            //set user
            User user = new User(username,password);
            model.setUser(user);
            user.setLoggedIn(true);
            //set gamelist
            model.setGameList(gameList);
        }

    }
}

