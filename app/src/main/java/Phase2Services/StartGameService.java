package Phase2Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import Communication.ServerProxy;
import Models.ActiveGame;
import Models.MainModel;
import Models.PendingGame;
import Phase2Models.DestinationCard;
import Phase2Models.Store;
import Phase2Models.TrainCardColor;
import Services.Service;

public class StartGameService implements Service {
    private MainModel model;

    public StartGameService(){
        model = MainModel.get();
    }

    @Override
    //params: String gameID
    public void connectToProxy(Object... obj) {
        String gameID = (String) obj[0];
        ServerProxy.get().startGame(gameID);
    }

    @Override
    //String gameID, String ipAddress, Store store, Map<ipAddress,DestinationCard[]> destcards, Map<ipAddress,TrainCard[]> trains
    public void doService(Object... obj) {
        //Check params
        if(obj.length != 5){
            model.setErrorMessage("Error Starting Game");
            System.out.println("ERROR: " + obj.length + " instead of 4 params on frontend login service");
        }

        String gameID = (String) obj[0];
        String ipAddress = (String) obj[1];
        Store store = (Store) obj[2]; //game store
        Map<String, DestinationCard[]> destCards = (Map <String, DestinationCard[]>) obj[3];
        Map<String, TrainCardColor[]> trainCards = (Map<String, TrainCardColor[]>) obj[4];

        PendingGame pg = model.findGame(gameID);
        if(pg != null){ //checks if this is user's game
            ActiveGame ag = model.getGameList().startGame(pg.getName()); //creates active game and removes pending from list
            ag.setStore(store);
            model.setActiveGame(ag);

            if(model.getIPAddress().equals(ipAddress)) { //if user
                //set player's destination card hand
                //ArrayList<DestinationCard> userDestCards = new ArrayList<>(Arrays.asList(destCards.get(model.getUser().getName())));
//                model.getPlayer().setDestHand(destCards.get(model.getUser().getName()));
                //set player's train card hand
//                ArrayList<TrainCardColor> userTrainCards = new ArrayList<>(Arrays.asList(trainCards.get(model.getUser().getName())));
//                model.getPlayer().setTrainHand(userTrainCards);
            }

        }


    }
}
