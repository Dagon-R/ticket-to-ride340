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
        String gameName = (String) obj[0];
        Player player = (Player) obj[1];

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
