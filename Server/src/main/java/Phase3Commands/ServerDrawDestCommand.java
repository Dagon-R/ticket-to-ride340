package Phase3Commands;

import Command.Command;
import Phase2Models.DestinationCard;
import Phase3Services.DrawDestService;

public class ServerDrawDestCommand implements Command {
    DestinationCard card;
    String ipAddress;
    @Override
    public Object execute(String gameID) {
        DrawDestService service = new DrawDestService();
        return service.doService(ipAddress,card);
    }

    @Override
    public void addResults(Object obj) {

    }

    @Override
    public void setIpAddress(String ipAddress) {

    }

    public ServerDrawDestCommand(){}
}
