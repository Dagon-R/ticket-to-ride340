package Command;

import Models.User;
import Services.CreateGameService;

public class CreateGameCommand implements Command {
    private User user;
    private String gameID;
    @Override
    public void execute() {
        CreateGameService newService = new CreateGameService();
        newService.doService(user, gameID);
    }
    public CreateGameCommand(User user, String gameID)
    {
        this.user = user;
        this.gameID = gameID;
    }
}
