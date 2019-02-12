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
        //Do some checks
        if(obj.length != 2){
            System.out.println("ERROR: " + obj.length + " instead of 2 params on backend leaveGame service");
            return new ErrorCommand("Error leaving game");
        }
        assert obj[0] instanceof Player : "leaveGame service wrong param 0 type";
        assert obj[1] instanceof String :  "leaveGame service wrong param 1 type";

        Player player = (Player) obj[0];
        String gameID = (String) obj[1];

        IGame game = model.getGameList().get(gameID);
        if(game.playerIsInGame(player)){
            game.removePlayer(player);
        } else {
            return new ErrorCommand("Cannot remove player because player is not in game");
        }

        return model.getGameList();
    }
}
