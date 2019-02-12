package Services;

import Communication.CommandManager;
import Communication.ServerProxy;
import Models.*;


public class CreateGameService implements Service {
    private ServerProxy sp;
    private MainModel model;

    public CreateGameService(){
        sp = new ServerProxy();
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {
        User host = (User) obj[0];
        String gameName = (String) obj[1];

        Player player = new Player(host.getName(), null);

        sp.createGame(player, gameName);
    }

    @Override
    public void doService(Object... obj) {
        //Check params
        System.out.println("In doService!");
        if(obj.length != 4){
            model.setErrorMessage("Error Creating Game");
            System.out.println("ERROR: " + obj.length + " instead of 4 params on frontend createGame service");
        }

        Player host = (Player) obj[0];
        String gameName = (String) obj[1];
        String ipAddress = (String) obj[2];
        ClientGameList gameList = (ClientGameList) obj[3];

        if(gameName.trim().equals("")){
            model.setErrorMessage("Enter a game name");
            return;
        }

        PendingGame newGame = new PendingGame(host, gameName);
        model.setGameList(gameList);
        if(model.getIPAddress().equals(ipAddress)){
            //this user created game
            model.setGame(newGame);
            System.out.println("model set");
        }

    }
}
