package Services;

import android.graphics.Paint;

import Communication.ServerProxy;
import Models.PendingGame;
import Models.Player;

public class JoinGameService implements Service {
    private ServerProxy sp;

    public JoinGameService(){
        sp = ServerProxy.get();
    }

    @Override
    public Object doService(Object... obj) {
        String gameName = (String) obj[0];
        Player player = (Player) obj[1];

        //call service
        //if good, add player to game's playerlist (frontend)
        PendingGame game = sp.JoinGame(player, gameName);

        //return valid or something
        return game;
    }
}
