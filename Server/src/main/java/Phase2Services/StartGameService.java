package Phase2Services;

import java.util.HashMap;

import Command.ErrorCommand;
import Models.ActiveGame;
import Models.MainModel;
import Models.ReturnObjects.StartGameReturn;
import Phase2Models.DestinationCard;
import Phase2Models.InvalidStoreLengthException;
import Phase2Models.Store;
import Phase2Models.TrainCardColor;
import Services.Service;

public class StartGameService implements Service {
    private MainModel model;

    public StartGameService(){
        model = MainModel.get();
    }

    @Override
    public Object doService(Object... obj) {
        String gameID = (String) obj[0];

        if(model.getGameList().get(gameID) == null){
            return new ErrorCommand("Game does not exist!");
        }

        if(model.getGameList().get(gameID).getPlayers().size() < 2){
            return new ErrorCommand("Not enough players!");
        }

        ActiveGame ag = model.getGameList().startGame(gameID);
        //divvy out destination cards
        HashMap<String, DestinationCard[]> cardMap = new HashMap<>();
        TrainCardColor drawnCards[] = new TrainCardColor[0];
        Store store = null;
        try {
            store = new Store(drawnCards);
        } catch (InvalidStoreLengthException e) {
            e.printStackTrace();
        }
        HashMap<String, TrainCardColor[]> drawnTrains = new HashMap<>();
//        for(Player player : ag.getPlayers()){
            //create map of players to cards
//            cardMap.put(player.getName(), ag.getDestDeck().draw3());
//        }

        return new StartGameReturn(gameID, store, drawnTrains, cardMap); //was gamelist
    }
}
