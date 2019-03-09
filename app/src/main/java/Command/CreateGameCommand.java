package Command;

import Models.PendingGame;
import Services.CreateGameService;

// This command allows a client to create a new pending game
public class CreateGameCommand implements Command {
    private String player; // The player who is creating the game
    private String gameID; // An identification string for the game being created
    private volatile String ipAddress; // The IP address of the user creating the game
    private volatile PendingGame pendingGame; // The pending game that was returned
    @Override // Calls the CreateGameService and tells it to create a new pending game
    public void execute() {
        // Create a new service
        CreateGameService newService = new CreateGameService();
        // Execute that service
        newService.doService(player, gameID, ipAddress, pendingGame);
    }
    public CreateGameCommand(String player, String gameID)
    // Creates a new command with a specific game name and starting player
    {
        this.player = player; // Set the player
        this.gameID = gameID; // Set the game ID
        this.ipAddress = null;
        this.pendingGame = null;
    }

    @Override
    public String toString() {
        return "CreateGameCommand{" +
                "\nplayer='" + player + '\'' +
                ", \ngameID='" + gameID + '\'' +
                ", \nipAddress='" + ipAddress + '\'' +
                ", \npendingGame='" + pendingGame + '\'' +
                '}';
    }
}
