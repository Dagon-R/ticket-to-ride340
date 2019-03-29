package Phase3Services;

import Communication.ServerProxy;
import Models.MainModel;
import Models.Player;
import Phase2Models.DestinationCard;
import Services.Service;

public class DrawDestService implements Service {
    private MainModel model;

    public DrawDestService(){
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {
        ServerProxy.get().drawDest(model.getPlayer().getName());
    }

    @Override
    //Params: String playerID, IDestCard card, String ipAddress
    public void doService(Object... obj) {
        DestinationCard[] cards = (DestinationCard[]) obj[0];
        String playerName = (String) obj[1];
        MainModel.get().getGame().getActiveGame().getPlayer(playerName).addToDestHand(cards);
    }

}
