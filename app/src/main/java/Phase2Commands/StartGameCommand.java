package Phase2Commands;

import java.util.HashMap;

import Command.Command;
import Phase2Models.DestinationCard;
import Phase2Models.Store;
import Phase2Services.StartGameService;

public class StartGameCommand implements Command {
    private Store store;
    private HashMap<String, DestinationCard[]> drawnCards;
    private String gameID;
    private String ipAddress;
    @Override
    public void execute() {
        StartGameService newService = new StartGameService();
        newService.doService(gameID, ipAddress, store, drawnCards);
    }
    public StartGameCommand(String gameID)
    {
        this.gameID = gameID;
    }
}
