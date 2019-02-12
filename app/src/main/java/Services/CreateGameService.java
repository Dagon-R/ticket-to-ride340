package Services;

import Communication.ServerProxy;
import Models.*;

public class CreateGameService implements Service {
    private ServerProxy sp;
    private MainModel model;

    public CreateGameService(){
        sp = ServerProxy.get();
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {
        User host = (User) obj[0];
        String gameName = (String) obj[1];

        sp.createGame(host, gameName);
    }

    @Override
    public void doService(Object... obj) {
        Player host = (Player) obj[0];
        String gameName = (String) obj[1];

        PendingGame newGame = new PendingGame(host, gameName);
        model.getGameList().addServerPendingGame(newGame);
        if(host.getName().equals(model.getUser().getName())){
            //this user created game
            model.setGame(newGame);
        }

    }
}
