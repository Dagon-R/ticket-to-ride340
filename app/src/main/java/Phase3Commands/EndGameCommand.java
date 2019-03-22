package Phase3Commands;

import Command.Command;
import Phase3Services.EndGameService;

public class EndGameCommand implements Command {
    private volatile String ipAddress;
    @Override
    public void execute() {
        EndGameService service = new EndGameService();
        service.doService(ipAddress);
    }
    public EndGameCommand(){}
}
