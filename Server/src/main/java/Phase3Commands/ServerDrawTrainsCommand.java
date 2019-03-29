package Phase3Commands;

import Command.Command;
import Phase2Models.TrainCardColor;
import Phase3Services.DrawTrainsService;

public class ServerDrawTrainsCommand implements Command {
    private volatile int card1;
    private volatile int card2;
    private volatile TrainCardColor return1 = null;
    private volatile TrainCardColor return2 = null;
    private String ipAddress;
    @Override
    public Object execute(String gameID) {
        DrawTrainsService service = new DrawTrainsService();
        return service.doService(gameID,card1,card2);
    }

    @Override
    public void addResults(Object obj) {
        TrainCardColor[] results = (TrainCardColor[]) obj;
        return1 = results[0];
        if (results.length > 1) {return2 = results[1];}
    }

    @Override
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public ServerDrawTrainsCommand(int pos1, int pos2)
    {
        card1 = pos1;
        card2 = pos2;
    }
}
