package Phase3Commands;

import Command.Command;
import Phase2Models.TrainCardColor;
import Phase3Services.DrawTrainsService;

public class DrawTrainsCommand implements Command {
    private int card1;
    private int card2;
    private TrainCardColor return1;
    private TrainCardColor return2;
    private String ipAddress;
    private String playerID;
    @Override
    public void execute() {
        DrawTrainsService service = new DrawTrainsService();
        service.doService(card1,card2,return1,return2,playerID);
    }
    public DrawTrainsCommand(String playerID, int pos1, int pos2)
    {
        this.playerID = playerID;
        card1 = pos1;
        card2 = pos2;
    }
}
