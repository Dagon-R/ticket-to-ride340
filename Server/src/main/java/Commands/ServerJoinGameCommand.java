package Commands;

import Services.Service;

public class ServerJoinGameCommand implements Command {
    public ServerJoinGameCommand() {
    }

    @Override
    public Object execute() {
        Service joinGameService = null;
        return joinGameService.doService();
    }
}
