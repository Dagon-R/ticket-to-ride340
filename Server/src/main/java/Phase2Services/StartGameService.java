package Phase2Services;

import java.util.HashMap;

import Command.ErrorCommand;
import Models.ActiveGame;
import Models.MainModel;
import Models.Player;
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

        if(model.getGameList().get(gameID).getPlayers().size() < 2){ //TODO: don't forget to uncomment
            return new ErrorCommand("Not enough players!");
        }

        ActiveGame ag = model.getGameList().startGame(gameID);
        //divvy out destination cards
        HashMap<String, DestinationCard[]> destMap = new HashMap<>();
        HashMap<String, TrainCardColor[]> trainMap = new HashMap<>();
        Store store = null;
        try{
            store = new Store(ag.getTrainDeck().drawStore());
            ag.setStore(store);
        } catch (InvalidStoreLengthException e){
            e.printStackTrace();
            return new ErrorCommand("Invalid store length in start game service");
        }

        for(String playerName : ag.getPlayers()){
            //create map of players to cards
            destMap.put(playerName, ag.getDestDeck().draw3());
            trainMap.put(playerName, ag.getTrainDeck().draw4());

        }

        return new StartGameReturn(gameID, store, trainMap, destMap); //was gamelist
    }
}
