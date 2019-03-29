package Phase3Services;

import Communication.ServerProxy;
import Models.MainModel;
import Phase2Models.DestinationCard;
import Services.Service;

public class DrawDestService implements Service {
    private MainModel model;
    public DrawDestService() {model = MainModel.get();}


    //Params: String playerID
    @Override
    public void connectToProxy(Object... obj) {
        String playerID = (String) obj[0];
        ServerProxy.get().drawDest(playerID);
    }

    //Param 1: String ipAddress
    //Param 2: DestinationCard card
    @Override
    public void doService(Object... obj) {
        String ipAddress = (String) obj[0];
        DestinationCard card = (DestinationCard) obj[1];
        String playerName = (String) obj[2];

        if (ipAddress.equals(model.getIPAddress())) {
            model.getPlayer().addToDestHand(card);
        } else{
            model.getGame().getActiveGame().getPlayer(playerName).addToDestHand(card);
        }
    }
}
