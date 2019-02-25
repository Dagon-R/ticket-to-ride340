package Phase2Commands;

import Command.Command;
import Phase2Models.DestinationCard;
import Phase2Services.DiscardDestCardService;

public class DiscardCardCommand implements Command {
    private String ipAddress;
    private DestinationCard toDiscard;
    @Override
    public void execute() {
        DiscardDestCardService discarder = new DiscardDestCardService();
        discarder.doService(toDiscard,ipAddress);
    }
    public DiscardCardCommand(DestinationCard toDiscard)
    {
        this.toDiscard = toDiscard;
    }
}
