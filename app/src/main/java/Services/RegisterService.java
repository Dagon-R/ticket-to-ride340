package Services;

import java.util.concurrent.TimeUnit;

import Communication.CommandManager;
import Communication.ServerProxy;
import Communication.SocketInitializer;
import Models.ClientGameList;
import Models.MainModel;

public class RegisterService implements Service {
    private MainModel model;
    private SocketInitializer si = new SocketInitializer();

    public RegisterService(){
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
//            while (true) {
//                try {
//                    TimeUnit.SECONDS.sleep((long) .002);
//                    if (CommandManager.get() != null) {
//                        break;
//                    }
//                } catch (InterruptedException e) {
//                    System.out.println("Error sleeping");
//                }
//            }
        }
        if (si.isFinished() == -1) {MainModel.get().setErrorMessage("Failed Connection"); return;}
        String authToken = username + Long.toString(System.currentTimeMillis());
        model.setAuthToken(authToken);

        ServerProxy.get().register(username, password, ipAddress, authToken);
    }

    @Override
    public void doService(Object... obj) {
        //Check params
        if(obj.length != 5){
            model.setErrorMessage("Error Registering");
            System.out.println("ERROR: " + obj.length + " instead of 5 params on frontend register service");
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

