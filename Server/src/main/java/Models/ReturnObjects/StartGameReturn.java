package Models.ReturnObjects;

import java.util.EnumMap;
import java.util.HashMap;

import Phase2Models.DestinationCard;
import Phase2Models.Store;
import Phase2Models.TrainCardColor;

public class StartGameReturn implements ReturnWrapper {
    private String gameID;
    private HashMap<String, EnumMap<TrainCardColor,Integer>> drawnTrains;
    private HashMap<String, DestinationCard[]> drawnDestCards;
    private Store store;

    public StartGameReturn(String gameID, Store store,
                           HashMap<String, EnumMap<TrainCardColor,Integer>> drawnTrains,
                           HashMap<String, DestinationCard[]> drawnDestCards)
    {
        this.gameID = gameID;
        this.drawnTrains = drawnTrains;
        this.drawnDestCards = drawnDestCards;
        this.store = store;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public HashMap<String, EnumMap<TrainCardColor,Integer>> getDrawnTrains() {
        return drawnTrains;
    }

    public void setDrawnTrains(HashMap<String, EnumMap<TrainCardColor,Integer>> drawnTrains) {
        this.drawnTrains = drawnTrains;
    }

    public HashMap<String, DestinationCard[]> getDrawnDestCards() {
        return drawnDestCards;
    }

    public void setDrawnDestCards(HashMap<String, DestinationCard[]> drawnDestCards) {
        this.drawnDestCards = drawnDestCards;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
