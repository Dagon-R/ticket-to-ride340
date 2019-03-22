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
    @Override
    public void execute() {
        DrawTrainsService service = new DrawTrainsService();
        service.doService(ipAddress,card1,card2,return1,return2);
    }
    public DrawTrainsCommand(int pos1, int pos2)
    {
        card1 = pos1;
        card2 = pos2;
    }
}
