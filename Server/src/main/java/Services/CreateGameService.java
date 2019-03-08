package Services;

import Command.ErrorCommand;
import Models.MainModel;
import Models.PendingGame;
import Models.Player;

public class CreateGameService implements Service {

    public CreateGameService() {
    }

    @Override
    public Object doService(Object... obj) {
        //Do some param checks
        if(obj.length != 2){
            System.out.println("ERROR: " + obj.length + " instead of 2 params on backend createGame service");
            return new ErrorCommand("Error creating game");
        }

        //create a game object
        String player = (String) obj[0];
        String gameID = (String) obj[1];
        //TODO: check that user exists
        if (MainModel.get().getGameList().get(gameID) != null)
        {
            return false;
        }
        else
        {
            PendingGame newGame = new PendingGame(player,gameID);
            MainModel.get().getGameList().addServerPendingGame(newGame);
            return newGame;
        }
    }
}
