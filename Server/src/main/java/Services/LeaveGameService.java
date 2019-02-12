package Services;

import Command.ErrorCommand;
import Models.IGame;
import Models.MainModel;
import Models.Player;

public class LeaveGameService implements Service {
    MainModel model;

    public LeaveGameService(){
        model = MainModel.get();
    }

    @Override
    public Object doService(Object... obj) {
        //Do some param checks
        if(obj.length != 2){
            System.out.println("ERROR: " + obj.length + " instead of 2 params on backend leaveGame service");
            return new ErrorCommand("Error leaving game");
        }

        Player player = (Player) obj[0];
        String gameID = (String) obj[1];

        IGame game = model.getGameList().get(gameID);
        //TODO: change active game to pending game
        if(game.playerIsInGame(player)){
            game.removePlayer(player);
        } else {
            return new ErrorCommand("Cannot remove player because player is not in game");
        }

        return model.getGameList();
    }
}
