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

        PendingGame game = model.findGame(gameName);
        int numPlayers = game.getPlayers().size();
        Player player = new Player(model.getUser().getName(), PlayerColorEnum.values()[numPlayers]);

        sp.joinGame(player, gameName);
    }

    @Override
    public void doService(Object... obj) {
        //Check params
        System.out.println("Create game do service");
        if(obj.length != 4){
            model.setErrorMessage("Error Joining Game");
            System.out.println("ERROR: " + obj.length + " instead of 4 params on frontend joinGame service");
        }
        Player player = (Player) obj[0];
        String gameName = (String) obj[1];
        String joined = (String) obj[2];
        String ipAddress = (String) obj[3];
        ClientGameList gameList = (ClientGameList) obj[4];

        model.addPlayerToGame(gameName, player);
        model.setGameList(gameList);

        //If this client
        if(model.getIPAddress().equals(ipAddress)){
            PendingGame game = new PendingGame(player, gameName);
            model.setGame(game);
        }
    }
}
