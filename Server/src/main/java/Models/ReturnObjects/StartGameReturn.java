package Models.ReturnObjects;

import java.util.HashMap;

import Models.GameList;
import Phase2Models.DestinationCard;

public class StartGameReturn implements ReturnWrapper {
    private GameList gameList;
    private HashMap<String, DestinationCard[]> cardMap;

    public StartGameReturn(GameList gameList, HashMap<String, DestinationCard[]> cardMap) {
        this.gameList = gameList;
        this.cardMap = cardMap;
    }

    public GameList getGameList() {
        return gameList;
    }

    public void setGameList(GameList gameList) {
        this.gameList = gameList;
    }

    public HashMap<String, DestinationCard[]> getCardMap() {
        return cardMap;
    }

    public void setCardMap(HashMap<String, DestinationCard[]> cardMap) {
        this.cardMap = cardMap;
    }
}
