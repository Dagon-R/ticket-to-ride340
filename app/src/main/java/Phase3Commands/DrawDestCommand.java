package Phase3Commands;

import Command.Command;
import Phase2Models.DestinationCard;
import Phase3Services.DrawDestService;

public class DrawDestCommand implements Command {
    DestinationCard card;
    String ipAddress;
    @Override
    public void execute() {
        DrawDestService service = new DrawDestService();
        service.doService(ipAddress,card);
    }
    public DrawDestCommand(){}
}
