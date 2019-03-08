package Services;

import android.graphics.Paint;

import Communication.CommandManager;
import Communication.ServerProxy;
import Models.*;

public class JoinGameService implements Service {
    private ServerProxy sp;
    private MainModel model;

    public JoinGameService(){
        sp = new ServerProxy();
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {
        String gameName = (String) obj[0];
        String username = (String) obj[1];

        PendingGame game = model.findGame(gameName);
        int numPlayers = game.getPlayers().size();
//        String player = new Player(model.getUser().getName(), PlayerColorEnum.values()[numPlayers]);

        sp.joinGame(username, gameName);
    }

    @Override
    public void doService(Object... obj) {
        //Check params
        if(obj.length != 5){
            model.setErrorMessage("Error Joining Game");
            System.out.println("ERROR: " + obj.length + " instead of 5 params on frontend joinGame service");
        }
        //Grab info
        String player = (String) obj[0];
        String gameName = (String) obj[1];
        String joined = (String) obj[2];
        String ipAddress = (String) obj[3];
        ClientGameList gameList = (ClientGameList) obj[4];
        //Everyone gets game list
        model.setGameList(gameList);
        //Adding player to game for everyone
        model.addPlayerToGame(gameName, player);



        if(model.getIPAddress().equals(ipAddress)){
            PendingGame game = gameList.get(gameName);
            model.setPendingGame(game);
        }
//        game.addPlayer(player);

    }
}
