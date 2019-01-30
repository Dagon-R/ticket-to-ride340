package Commands;

import Services.Service;

public class ServerCreateGameCommand implements Command {
    public ServerCreateGameCommand() {
    }

    @Override
    public Object execute() {
        Service createGameService = null;
        return createGameService.doService();
    }
}
