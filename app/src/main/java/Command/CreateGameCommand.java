package Command;


import Models.User;
import Services.CreateGameService;

public class CreateGameCommand implements Command {
    User user;
    String gameID;
    @Override
    public Object execute() {
        CreateGameService newService = new CreateGameService();
        return newService.doService(user, gameID);
    }
    public CreateGameCommand(User user, String gameID)
    {
        this.user = user;
        this.gameID = gameID;
    }
}
