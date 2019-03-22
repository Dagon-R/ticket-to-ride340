package Phase3Commands;

import Command.Command;
import Phase2Models.TrainCardColor;
import Phase3Services.DrawTrainsService;

public class ServerDrawTrainsCommand implements Command {
    private int card1;
    private int card2;
    private TrainCardColor return1;
    private TrainCardColor return2;
    private String ipAddress;
    @Override
    public Object execute() {
        DrawTrainsService service = new DrawTrainsService();
        return service.doService(ipAddress,card1,card2,return1,return2);
    }

    @Override
    public void addResults(Object obj) {

    }

    @Override
    public void setIpAddress(String ipAddress) {

    }

    public ServerDrawTrainsCommand(int pos1, int pos2)
    {
        card1 = pos1;
        card2 = pos2;
    }
}
