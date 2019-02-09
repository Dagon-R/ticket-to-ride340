package Services;

import Models.MainModel;
import Models.PendingGame;
import Models.Player;

public class CreateGameService implements Service {

    public CreateGameService(Player player, String game) {
    }

    @Override
    public Object doService(Object... obj) {
        //create a game object
        Player player = (Player) obj[0];
        String gameID = (String) obj[1];
        if (MainModel.get().getGameList().get(gameID) != null)
        {
            return false;
        }
        else
        {
            PendingGame newGame = new PendingGame(player,gameID);
            MainModel.get().getGameList().addServerPendingGame(newGame);
            return true;
        }
    }
}
