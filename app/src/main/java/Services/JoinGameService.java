package Services;

import android.graphics.Paint;

import Communication.ServerProxy;
import Models.*;

public class JoinGameService implements Service {
    private ServerProxy sp;
    private MainModel model;

    public JoinGameService(){
        sp = ServerProxy.get();
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {
        //Check params
        if(obj.length != 1){
            model.setErrorMessage("Error Joining Game");
            System.out.println("ERROR: " + obj.length + " instead of 1 params on frontend login service");
        }
        assert obj[0] instanceof String;

        String gameName = (String) obj[0];

        PendingGame game = model.findGame(gameName);
        int numPlayers = game.getPlayers().size();
        Player player = new Player(model.getUser().getName(), PlayerColorEnum.values()[numPlayers]);

        sp.joinGame(player, gameName);
    }

    @Override
    public void doService(Object... obj) {
        String gameName = (String) obj[0];
        Player player = (Player) obj[1];

        if(player.getName() == model.getUser().getName()){
            PendingGame game = new PendingGame(player, gameName);
            model.setGame(game);
        }
    }
}
