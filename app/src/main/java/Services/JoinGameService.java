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
        if(obj.length != 3){
            model.setErrorMessage("Error Joining Game");
            System.out.println("ERROR: " + obj.length + " instead of 3 params on frontend joinGame service");
        }
        String gameName = (String) obj[0];
        Player player = (Player) obj[1];
        String ipAddress = (String) obj[2];

        PendingGame game = new PendingGame(player, gameName);
        game.addPlayer(player);

        //If this client
        if(CommandManager.get().getOwnIP().equals(ipAddress)){
            model.setGame(game);
        }
    }
}
