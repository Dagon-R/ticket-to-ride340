package Phase2Commands;

import Command.Command;
import Models.MainModel;
import Phase2Models.DestinationCard;
import Phase2Services.DiscardDestService;

public class DiscardDestCommand implements Command {
    private volatile String ipAddress;
    private volatile boolean success;
    private volatile String gameId;
    private DestinationCard toDiscard;
    @Override
    public void execute() {
        if (success)
        {
            DiscardDestService discarder = new DiscardDestService();
            discarder.doService(toDiscard,gameId);
        }
        else
        {
            System.out.println("Failed to discard card");
        }
    }
    public DiscardDestCommand(DestinationCard toDiscard)
    {
        this.toDiscard = toDiscard;
        this.gameId = MainModel.get().getGame().getActiveGame().getId();
    }
}
