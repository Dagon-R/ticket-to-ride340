package Phase3Commands;

import Command.Command;
import Phase3Services.EndGameService;

public class ServerEndGameCommand implements Command {
    private String ipAddress;
    @Override
    public Object execute() {
        EndGameService service = new EndGameService();
        return service.doService();
    }

    @Override
    public void addResults(Object obj) { }

    @Override
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
