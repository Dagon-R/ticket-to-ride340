package Command;

import Models.Player;
import Models.User;
import Services.CreateGameService;

public class CreateGameCommand implements Command {
    Player player;
    String gameID;
    @Override
    public Object execute() {
        CreateGameService newService = new CreateGameService();
        return newService.doService(player, gameID);
    }
    public CreateGameCommand(Player player, String gameID)
    {
        this.player = player;
        this.gameID = gameID;
    }
}
