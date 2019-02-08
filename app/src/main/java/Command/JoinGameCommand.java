package Command;

import Models.Player;
import Services.JoinGameService;

public class JoinGameCommand implements Command {
    private Player player;
    private String gameID;

    @Override
    public Object execute() {
        JoinGameService newService = new JoinGameService();
        return newService.doService(player, gameID);
    }

    public JoinGameCommand(Player player, String gameID) {
        this.player = player;
        this.gameID = gameID;
    }
}