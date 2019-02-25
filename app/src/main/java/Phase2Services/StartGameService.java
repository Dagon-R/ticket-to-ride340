package Phase2Services;

import Communication.ServerProxy;
import Models.ActiveGame;
import Models.MainModel;
import Models.PendingGame;
import Services.Service;

public class StartGameService implements Service {
    private ServerProxy sp;
    private MainModel model;

    public StartGameService(){
        sp = new ServerProxy();
        model = MainModel.get();
    }

    @Override
    //params: String gameID
    public void connectToProxy(Object... obj) {
        String gameID = (String) obj[0];
        sp.startGame(gameID);
    }

    @Override
    //Params: Store store, Map<ipAddress,DestinationCard[]> drawnCards, String gameID, String ipAddress
    public void doService(Object... obj) {
        //Check params
        if(obj.length != 2){
            model.setErrorMessage("Error Starting Game");
            System.out.println("ERROR: " + obj.length + " instead of 2 params on frontend login service");
        }

        String gameID = (String) obj[0];
        String ipAddress = (String) obj[2];

        PendingGame pg = model.findGame(gameID);
        if(pg != null){
            ActiveGame ag = new ActiveGame(pg);
            model.setGame(ag);
            //TODO: remove pg from gameList?
        }

    }
}
