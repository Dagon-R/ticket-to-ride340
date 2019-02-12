package Services;

import Command.ErrorCommand;
import Models.ActiveGame;
import Models.IGame;
import Models.MainModel;
import Models.PendingGame;

public class StartGameService implements Service {
    MainModel model;

    public StartGameService(){
        model = MainModel.get();
    }

    @Override
    public Object doService(Object... obj) {
        String gameID = (String) obj[0];

        if(model.getGameList().get(gameID) == null){
            return new ErrorCommand("Game does not exist!");
        }

        if(model.getGameList().get(gameID).getPlayers().size() < 2){
            return new ErrorCommand("Not enough players!");
        }

        model.getGameList().startGame(gameID);

        return MainModel.get().getGameList();
    }
}
