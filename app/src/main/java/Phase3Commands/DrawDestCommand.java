package Phase3Commands;

import Command.Command;
import Phase2Models.DestinationCard;
import Phase3Services.DrawDestService;

public class DrawDestCommand implements Command {
    volatile private DestinationCard[] cards;
    volatile private String playerName;
    @Override
    public void execute() {
        DrawDestService service = new DrawDestService();
        service.doService(cards,playerName);
    }
    public DrawDestCommand(String playerName){this.playerName = playerName;}
}
