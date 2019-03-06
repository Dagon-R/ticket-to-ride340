package Phase2Services;

import java.util.HashMap;

import Command.ErrorCommand;
import Models.ActiveGame;
import Models.MainModel;
import Models.Player;
import Phase2Models.DestinationCard;
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
        for(Player player : ag.getPlayers()){
            //create map of players to cards
            cardMap.put(player.getName(), ag.getDestDeck().draw3());
        }

        return cardMap; //was gamelist
    }
}
