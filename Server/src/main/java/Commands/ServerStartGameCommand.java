package Commands;

import Services.Service;

public class ServerStartGameCommand implements Command {
    public ServerStartGameCommand() {
    }

    @Override
    public Object execute() {
        Service startGameService = null;

        return startGameService.doService();
    }
}
