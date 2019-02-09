package Command;


import Models.Player;
import Services.CreateGameService;

public class CreateGameCommand implements Command {
    private Player player;
    private String gameID;
    @Override
    public Object execute() {
        CreateGameService newService = new CreateGameService();
        return newService.doService(player);
    }
    public CreateGameCommand(Player player, String gameID)
    {
        this.player = player;
        this.gameID = gameID;
    }
}
