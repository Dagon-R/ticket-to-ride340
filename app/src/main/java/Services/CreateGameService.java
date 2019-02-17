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
        Player player = (Player) obj[0];
        String gameName = (String) obj[1];

        //call server initializer do in background
        //while sp.get == null
            //keep checking
        //when not null, go on

        sp.createGame(player, gameName);
    }

    @Override
    public void doService(Object... obj) {
        //Check params
        if(obj.length != 3){
            model.setErrorMessage("Error Creating Game");
            System.out.println("ERROR: " + obj.length + " instead of 3 params on frontend createGame service");
        }

        Player host = (Player) obj[0];
        String gameName = (String) obj[1];
        String ipAddress = (String) obj[2];

        PendingGame newGame = new PendingGame(host, gameName);
        model.getGameList().addServerPendingGame(newGame);
        if(CommandManager.get().getOwnIP().equals(ipAddress)){
            //this user created game
            model.setGame(newGame);
        }

    }
}
