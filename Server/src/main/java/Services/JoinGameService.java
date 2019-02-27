package Services;

import Command.ErrorCommand;
import Models.IGame;
import Models.MainModel;
import Models.PendingGame;
import Models.Player;

public class JoinGameService implements Service {
    //Params: player, gameID
    @Override
    public Object doService(Object... obj) {
        Player player = (Player) obj[0];
        String gameID = (String) obj[1];
        IGame game = MainModel.get().getGameList().get(gameID);
        if (game != null)
        {
            if (game.getClass() == PendingGame.class)
            {
                //TODO: check that user exists
                game.addPlayer(player);
                System.out.println("Player added to game");
                return MainModel.get().getGameList();
            }
            else
            {
                return new ErrorCommand("Game already started");
            }
        }
        else
        {
            return new ErrorCommand("Game doesn't exist");
        }
    }
}
