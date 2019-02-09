package Command;

import Services.StartGameService;

public class StartGameCommand implements Command {
    private String gameID;
    @Override
    public void execute() {
        StartGameService newService = new StartGameService();
        newService.doService(gameID);
    }
    public StartGameCommand(String gameID)
    {
        this.gameID = gameID;
    }
}
