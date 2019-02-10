package Command;

import Models.Player;
import Models.User;
import Services.CreateGameService;

public class CreateGameCommand implements Command {
    User user;
    String gameID;
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
