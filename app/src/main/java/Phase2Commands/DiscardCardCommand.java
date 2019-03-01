package Phase2Commands;

import Command.Command;
import Phase2Models.DestinationCard;
import Phase2Services.DiscardCardService;

public class DiscardCardCommand implements Command {
    private volatile String ipAddress;
    private DestinationCard toDiscard;
    @Override
    public void execute() {
        DiscardCardService discarder = new DiscardCardService();
        discarder.doService(toDiscard,ipAddress);
    }
    public DiscardCardCommand(DestinationCard toDiscard)
    {
        this.toDiscard = toDiscard;
    }
}
