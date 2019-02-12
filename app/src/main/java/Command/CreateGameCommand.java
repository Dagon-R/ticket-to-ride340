package Command;

import Models.UnobservedUser;
import Models.User;
import Services.CreateGameService;

public class CreateGameCommand implements Command {
    private UnobservedUser user;
    private String gameID;
    @Override
    public void execute() {
        CreateGameService newService = new CreateGameService();
        newService.doService(new User(user), gameID);
    }
    public CreateGameCommand(User user, String gameID)
    {
        this.user = new UnobservedUser(user);
        this.gameID = gameID;
    }
}
