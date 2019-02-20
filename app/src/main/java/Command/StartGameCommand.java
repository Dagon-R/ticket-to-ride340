package Command;

import Services.StartGameService;

public class StartGameCommand implements Command {
    private String gameID;
    private String ipAddress;
    @Override
    public void execute() {
        StartGameService newService = new StartGameService();
        newService.doService(gameID, ipAddress);
    }
    public StartGameCommand(String gameID)
    {
        this.gameID = gameID;
    }
}
