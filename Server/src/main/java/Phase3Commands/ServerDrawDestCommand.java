package Phase3Commands;

import Command.Command;
import Phase2Models.DestinationCard;
import Phase3Services.DrawDestService;

public class ServerDrawDestCommand implements Command {
    private DestinationCard[] cards;
    private String ipAddress;
    private volatile String playerName;
    @Override
    public Object execute(String gameID) {
        DrawDestService service = new DrawDestService();
        return service.doService(gameID, playerName);
    }

    @Override
    public void addResults(Object obj) {
        this.cards = (DestinationCard[]) obj;
    }

    @Override
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public ServerDrawDestCommand(){}
}
