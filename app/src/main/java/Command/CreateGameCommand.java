package Command;


import Models.Player;
import Services.CreateGameService;

public class CreateGameCommand implements Command {
    Player player;

    @Override
    public Object execute() {
        CreateGameService newService = new CreateGameService();
        return newService.doService(player);
    }
    public CreateGameCommand(Player player)
    {
        this.player = player;
    }
}
