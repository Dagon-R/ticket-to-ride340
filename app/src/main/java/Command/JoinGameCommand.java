package Command;

import Models.Player;
import Services.JoinGameService;

public class   JoinGameCommand implements Command {
    private Player player;
    private String gameID;
    private boolean joined;

    @Override
    public void execute() {
        JoinGameService newService = new JoinGameService();
        newService.doService(player, gameID);
    }

    public JoinGameCommand(Player player, String gameID) {
        this.player = player;
        this.gameID = gameID;
        this.joined = false;
    }

    public boolean isJoined() {
        return joined;
    }
}