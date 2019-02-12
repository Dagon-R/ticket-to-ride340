package Command;

import Models.ClientGameList;
import Models.Player;
import Models.UnobservedUser;
import Models.User;
import Services.CreateGameService;

public class CreateGameCommand implements Command {
    private Player player;
    private String gameID;
    private String ipAddress;
    private ClientGameList gameList;
    @Override
    public void execute() {
        CreateGameService newService = new CreateGameService();
        newService.doService(player, gameID, ipAddress, gameList);
    }
    public CreateGameCommand(Player player, String gameID)
    {
        this.player = player;
        this.gameID = gameID;
    }
}
