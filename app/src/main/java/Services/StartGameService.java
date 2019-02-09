package Services;

import Communication.ServerProxy;
import Models.ActiveGame;
import Models.MainModel;
import Models.PendingGame;

public class StartGameService implements Service {
    ServerProxy sp;
    MainModel model;

    public StartGameService(){
        sp = ServerProxy.get();
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {
        String gameID = (String) obj[0];
        sp.startGame(gameID);
    }

    @Override
    public void doService(Object... obj) {
        String gameID = (String) obj[0];

        PendingGame pg = model.findGame(gameID);
        if(pg != null){
            ActiveGame ag = new ActiveGame(pg);
            model.setGame(ag);
            //TODO: remove pg from gameList?
        }

    }
}
