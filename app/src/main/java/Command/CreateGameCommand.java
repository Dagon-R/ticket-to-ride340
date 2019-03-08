package Command;

import Models.ClientGameList;
import Models.Player;
import Services.CreateGameService;

// This command allows a client to create a new pending game
public class CreateGameCommand implements Command {
    private Player player; // The player who is creating the game
    private String gameID; // An identification string for the game being created
    private volatile String ipAddress; // The IP address of the user creating the game
    //TODO: Remove this value, add to everyone's gameList by gameID
    private volatile ClientGameList gameList; // The current list of games
    @Override // Calls the CreateGameService and tells it to create a new pending game
    public void execute() {
        // Create a new service
        CreateGameService newService = new CreateGameService();
        // Execute that service
        newService.doService(player, gameID, ipAddress, gameList);
    }
    // Creates a new command with a specific game name and starting player
    public CreateGameCommand(Player player, String gameID)
    {
        this.player = player; // Set the player
        this.gameID = gameID; // Set the game ID
    }
}
