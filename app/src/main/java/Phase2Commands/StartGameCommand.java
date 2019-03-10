package Phase2Commands;

import java.util.EnumMap;
import java.util.HashMap;

import Command.Command;
import Phase2Models.DestinationCard;
import Phase2Models.Store;
import Phase2Models.TrainCardColor;
import Phase2Services.StartGameService;

public class StartGameCommand implements Command {
    private volatile Store store;
    private volatile HashMap<String, DestinationCard[]> drawnCards;
    private volatile HashMap<String, EnumMap<TrainCardColor,Integer>> drawnTrains;
    private String gameID;
    private volatile String ipAddress;
    @Override
    public void execute() {
        StartGameService newService = new StartGameService();
        newService.doService(gameID, ipAddress, store, drawnCards, drawnTrains);
    }
    public StartGameCommand(String gameID)
    {
        this.gameID = gameID;
    }
}
