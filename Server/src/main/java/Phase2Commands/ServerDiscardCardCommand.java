package Phase2Commands;

import Command.Command;
import Phase2Models.DestinationCard;
import Phase2Services.DiscardCardService;

public class ServerDiscardCardCommand implements Command {
    private String ipAddress;
    private DestinationCard toDiscard;

    @Override
    public Object execute() {
        DiscardCardService discardCardService = new DiscardCardService();
        return discardCardService.doService(toDiscard,ipAddress);
    }

    @Override
    public void addResults(Object obj) {

    }

    @Override
    public void setIpAddress(String ipAddress) {

    }
}
