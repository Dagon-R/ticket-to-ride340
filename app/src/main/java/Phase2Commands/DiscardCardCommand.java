package Phase2Commands;

import Command.Command;
import Models.MainModel;
import Phase2Models.DestinationCard;
import Phase2Services.DiscardDestCardService;

public class DiscardCardCommand implements Command {
    private volatile String ipAddress;
    private volatile boolean success;
    private volatile String gameId;
    private DestinationCard toDiscard;
    @Override
    public void execute() {
        if (success)
        {
            DiscardDestCardService discarder = new DiscardDestCardService();
            discarder.doService(toDiscard,gameId);
        }
        else
        {
            System.out.println("Failed to discard card");
        }
    }
    public DiscardCardCommand(DestinationCard toDiscard)
    {
        this.toDiscard = toDiscard;
        this.gameId = MainModel.get().getGame().getId();
    }
}
