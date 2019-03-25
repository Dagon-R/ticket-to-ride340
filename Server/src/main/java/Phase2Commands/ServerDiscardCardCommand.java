package Phase2Commands;

import Command.Command;
import Phase2Models.DestinationCard;
import Phase2Services.DiscardCardService;

public class ServerDiscardCardCommand implements Command {
    private volatile String ipAddress;
    private volatile String gameId;
    private volatile DestinationCard toDiscard;
    private volatile boolean success;

    @Override
    public Object execute(String gameID) {
        DiscardCardService discardCardService = new DiscardCardService();
        return discardCardService.doService(toDiscard, this.gameId);
    }

    @Override
    public String getGameID() {
        return gameId;
    }

    @Override
    public void addResults(Object obj) {
        success = (Boolean) obj;
    }

    @Override
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
