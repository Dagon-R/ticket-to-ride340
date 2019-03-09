package Services;

import Command.ErrorCommand;

import Models.MainModel;
import Models.PendingGame;

public class JoinGameService implements Service {
    //Params: player, gameID
    @Override
    public Object doService(Object... obj) {
        String player = (String) obj[0];
        String gameID = (String) obj[1];
        PendingGame game = MainModel.get().getGameList().getPendingGame(gameID);
        if (game != null)
        {
            if (game.getClass() == PendingGame.class)
            {
                //TODO: check that user exists
                game.addPlayer(player);
                System.out.println("Player added to game");
                return game;
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
