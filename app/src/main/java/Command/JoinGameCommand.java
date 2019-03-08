package Command;

import Models.ClientGameList;
import Models.Player;
import Services.JoinGameService;

public class   JoinGameCommand implements Command {
    private String player;
    private String gameID;
    private boolean joined;
    private String ipAddress;
    private ClientGameList gameList;


    @Override
    public void execute() {
        JoinGameService newService = new JoinGameService();
        newService.doService(player, gameID, joined, ipAddress, gameList);
    }

    public JoinGameCommand(String player, String gameID) {
        this.player = player;
        this.gameID = gameID;
        this.joined = false;
        this.gameList = null;

    }

    public boolean isJoined() {
        return joined;
    }
}