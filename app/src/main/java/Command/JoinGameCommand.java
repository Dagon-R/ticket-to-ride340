package Command;

import Models.ClientGameList;
import Models.Player;
import Services.JoinGameService;

// Command that is used to add a player to a game
public class JoinGameCommand implements Command {
    private Player player; // The player joining the game
    private String gameID; // The game being joined
    //TODO: Remove "joined" from the program
    private boolean joined; // Whether or not the join was successful
    private volatile String ipAddress; // The IP Address of the user joining the game (set by Server)
    // The new list of pending games after this game was joined
    //TODO: Use the Game ID to increment values instead of the WHOLE GAME LIST
    private volatile ClientGameList gameList;

    @Override // If the player matches, join game, otherwise increment size of game in game list
    public void execute() {
        // Creates a new service
        JoinGameService newService = new JoinGameService();
        // Commands the service to add the player to the game
        newService.doService(player, gameID, joined, ipAddress, gameList);
    }
    // Create a new Join Game Command from the commanding player and the game name
    public JoinGameCommand(Player player, String gameID) {
        this.player = player; // Set the commanding player
        this.gameID = gameID; // Set the game name
        this.joined = false; // Before returning from the server, the join is a fail

    }
}