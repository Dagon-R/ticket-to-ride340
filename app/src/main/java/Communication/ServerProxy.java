package Communication;

import Command.*;
import Phase2Commands.ChatCommand;
import Phase2Commands.ClaimRouteCommand;
import Phase2Commands.DiscardCardCommand;
import Phase2Commands.StartGameCommand;
import Phase2Models.ChatMessage;
import Phase2Models.DestinationCard;
import Phase2Models.Route;

//Anytime you would call a method on the Server, you call that method on this class instead. This
//only really sends the command to the server, the updates that come back are performed in the
//client services (initiated by the Client Poller)
public class ServerProxy {

    private static ServerProxy sp = null; // Static instance of this singleton

    public static ServerProxy get() // Returns the static instance of this singleton
    {
        if (sp == null) // If the singleton doesn't exist yet,
        { // Make it
            return sp = new ServerProxy();
        }
        else {return sp;} // Otherwise, return it
    }

    private ServerProxy() { } // Private constructor (singleton)

    // Tells the server to log someone in
    public void login(String username, String password, String ipAddress, String authToken)
    {
        TheLoginCommand newLog = new TheLoginCommand(username,password,ipAddress, authToken);
        SendCommand sc2 = new SendCommand(newLog);
        sc2.start();
    }
    // Tells the server to register someone
    public void register(String username,String password, String ipAddress, String authToken)
    {
        RegisterCommand newReg = new RegisterCommand(username,password,ipAddress, authToken);
        SendCommand sc2 = new SendCommand(newReg);
        sc2.start();
    }
    // Tells the server to add someone into a game
    public void joinGame(String player, String gameID)
    {
        JoinGameCommand newJoin =
                new JoinGameCommand(player,gameID);
        SendCommand sc2 = new SendCommand(newJoin);
        sc2.start();
    }
    // Tells the server to start a game
    public void startGame(String gameID)
    {
        StartGameCommand newStart = new StartGameCommand(gameID);
        SendCommand sc2 = new SendCommand(newStart);
        sc2.start();
    }
    // Tells the server to create a new game
    public void createGame(String player, String gameID)
    {
        CreateGameCommand newCreate =
                new CreateGameCommand(player, gameID);
        SendCommand sc2 = new SendCommand(newCreate);
        sc2.start();

    }
    // Tells the server to send a chat message
    public void chat(ChatMessage message, String gameID)
    {
        ChatCommand newChat = new ChatCommand(message, gameID);
        SendCommand sc2 = new SendCommand(newChat);
        sc2.start();
    }
    // Tells the server to claim a route
    public void claimRoute(Route route, String playerName)
    {
        ClaimRouteCommand newClaim = new ClaimRouteCommand(route,playerName);
        SendCommand sc2 = new SendCommand(newClaim);
        sc2.start();
    }
    // Tells the server to discard a card
    public void discardCard(DestinationCard card)
    {
        DiscardCardCommand newDiscard = new DiscardCardCommand(card);
        SendCommand sc2 = new SendCommand(newDiscard);
        sc2.start();
    }

}
