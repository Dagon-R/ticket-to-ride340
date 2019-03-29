package Phase3Services;

import Models.MainModel;
import Services.Service;

public class DrawDestService implements Service {
    private MainModel model;
    public DrawDestService() {model = MainModel.get();}

    //Param 1: String ipAddress
    //Param 2: DestinationCard card
    @Override
    public Object doService(Object... obj) {
        String gameID = (String) obj[0];
        return model.getGameList().getActiveGame(gameID).getDestDeck().draw3();
    }
}
