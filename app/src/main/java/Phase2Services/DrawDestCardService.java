package Phase2Services;

import Communication.ServerProxy;
import Models.MainModel;
import Models.PendingGame;
import Phase2Models.DestinationCard;
import Services.Service;

public class DrawDestCardService implements Service {
    private MainModel model;

    public DrawDestCardService(){
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {
        ServerProxy.get().drawDest();
    }

    @Override
    //TODO: need gameID and numDestCards
    //Params: String playerID, IDestCard card, String ipAddress
    public void doService(Object... obj) {
        //Check params
        if(obj.length != 3){
            model.setErrorMessage("Error Drawing Destination Card");
            System.out.println("ERROR: " + obj.length + " instead of 3 params on frontend drawDestCard service");
        }

        String playerID = (String) obj[0];
        DestinationCard card = (DestinationCard) obj[1];
        String ipAddress = (String) obj[2];

        //TODO: update number of destination cards
//        PendingGame pg = model.findGame(gameID);
//        if(pg != null) { //checks if this is user's game
//
//
//        }

        //if player, add card to hand
        if(model.getIPAddress().equals(ipAddress)) { //if user
            model.getPlayer().addToDestHand(card);
        }

    }

}
