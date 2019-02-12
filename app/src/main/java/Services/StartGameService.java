package Services;

import Communication.ServerProxy;
import Models.ActiveGame;
import Models.MainModel;
import Models.PendingGame;

public class StartGameService implements Service {
    private ServerProxy sp;
    private MainModel model;

    public StartGameService(){
        sp = new ServerProxy();
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {
        String gameID = (String) obj[0];
        sp.startGame(gameID);
    }

    @Override
    public void doService(Object... obj) {
        //Check params
        if(obj.length != 1){
            model.setErrorMessage("Error Starting Game");
            System.out.println("ERROR: " + obj.length + " instead of 1 params on frontend login service");
        }

        String gameID = (String) obj[0];

        PendingGame pg = model.findGame(gameID);
        if(pg != null){
            ActiveGame ag = new ActiveGame(pg);
            model.setGame(ag);
            //TODO: remove pg from gameList?
        }

    }
}
