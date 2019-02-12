package Services;

import java.net.InetAddress;
import java.net.UnknownHostException;

import Communication.ServerProxy;
import Communication.SocketConnectionError;
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

//        try{
//            sp = ServerProxy.create(ipAddress);
//            sp.login(username, password, ipAddress);
//
//        } catch(SocketConnectionError e){
//            model.setErrorMessage("Error connecting to socket");
//        }
    }

    @Override
    public void doService(Object... obj) {
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

