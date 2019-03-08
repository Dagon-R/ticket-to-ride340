package Phase2Services;

import Models.MainModel;
import Phase2Models.DestinationCard;
import Services.Service;

public class DrawDestCardService implements Service {
    private MainModel model;

    public DrawDestCardService(){
        model = MainModel.get();
    }

    @Override
    //Params: String playerID
    public void connectToProxy(Object... obj) {

    }

    @Override
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

        //if player, add card to hand
        if(model.getIPAddress().equals(ipAddress)) { //if user
            model.getPlayer().addToDestHand(card);
        }

    }

}
