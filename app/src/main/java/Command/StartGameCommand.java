package Command;

import Services.StartGameService;

public class StartGameCommand implements Command {
    String gameID;
    @Override
    public Object execute() {
        StartGameService newService = new StartGameService();
        return newService.doService(gameID);
    }
    public StartGameCommand(String gameID)
    {
        this.gameID = gameID;
    }
}
