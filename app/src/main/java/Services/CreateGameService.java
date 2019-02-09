package Services;

import Communication.ServerProxy;
import Models.MainModel;
import Models.PendingGame;
import Models.Player;

public class CreateGameService implements Service {
    ServerProxy sp;
    MainModel model;

    public CreateGameService(){
        sp = ServerProxy.get();
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {

    }

    @Override
    public Object doService(Object... obj) {
        Player host = (Player) obj[0];
        String gameName = (String) obj[1];

        //create gameID? Or get from client

        //create pendingGame object
        //PendingGame newGame = new PendingGame(host, gameName);

        //call service, passback pendingGame
        //sp.createGame(host, gameName);

        //TODO:if returns ok, add pendingGame to list

        //return pendingGame
        return null;
    }
}
