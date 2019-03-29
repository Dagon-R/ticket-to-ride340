package Phase3Services;

import Models.MainModel;
import Phase2Models.DestinationCard;
import Services.Service;

public class DrawDestService implements Service {
    private MainModel model;
    public DrawDestService() {model = MainModel.get();}

    //Param 1: String ipAddress
    //Param 2: DestinationCard card X needs String gameName
    @Override
    public Object doService(Object... obj) {
        //DestinationCard card = (DestinationCard) obj[0];
        String gameName = (String) obj[1];

        DestinationCard[] cards = model.getGameList().getActiveGame(gameName).getDestDeck().draw3();
        return cards;
    }
}
