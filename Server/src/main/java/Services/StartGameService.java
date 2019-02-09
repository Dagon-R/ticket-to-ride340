package Services;

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

        IGame game = model.getGameList().get(gameID);
        if(game != null && game.getClass().equals(PendingGame.class)){
            ActiveGame ag = new ActiveGame(game);

        }
        //get rid of pendingGame and change to activeGame
        //return valid message
        //-users need to all be listening for startGame
        return null;
    }
}
