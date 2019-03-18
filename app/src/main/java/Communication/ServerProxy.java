package Communication;

import Command.*;
import Phase2Commands.ChatCommand;
import Phase3Commands.ClaimRouteCommand;
import Phase2Commands.DiscardDestCommand;
import Phase2Commands.StartGameCommand;
import Phase2Models.ChatMessage;
import Phase2Models.DestinationCard;
import Phase2Models.Route;
import Phase3Commands.DrawDestCommand;
import Phase3Commands.DrawTrainsCommand;

//Anytime you would call a method on the Server, you call that method on this class instead. This
//only really sends the command to the server, the updates that come back are performed in the
//client services (initiated by the Client Poller)
public class ServerProxy {

    private static ServerProxy sp = null; // Static instance of this singleton

    //@Pre: None
    //@Post: ServerProxy sp != null
    //@Return: sp
    public static ServerProxy get() // Returns the static instance of this singleton
    {
        if (sp == null) // If the singleton doesn't exist yet,
        { // Make it
            return sp = new ServerProxy();
        }
        else {return sp;} // Otherwise, return it
    }

    //@Pre: Called from within get()
    //@Post: New ServerProxy object
    //@Return: New ServerProxy object
    private ServerProxy() { } // Private constructor (singleton)

    //@Pre: Non-Null username, password, ipAddress, and authToken
    //@Pre: authToken matches authToken set in MainModel
    //@Pre: User is not set in MainModel, client is in the MainActivity
    //@Post: If ipAddress is not the address of a running server, a toast is popped
    //@Post: If the user with the username and password provided doesn't exist on the server with
    // the given ipAddress, a toast is popped saying so
    //@Post: If the user with the provided username and password exists on the server with the
    // given ipAddress, the client is moved into the ChooseGameActivity and the current user is set
    // to be the matching user
    
    public void login(String username, String password, String ipAddress, String authToken)
    {
        TheLoginCommand newLog = new TheLoginCommand(username,password,ipAddress, authToken);
        SendCommand sc2 = new SendCommand(newLog);
        sc2.start();
    }
    //@Pre: Non-Null username, password, ipAddress, and authToken
    //@Pre: authToken matches authToken set in MainModel
    //@Pre: User is not set in MainModel, client is in the MainActivity
    //@Post: If ipAddress is not the address of a running server, a toast is popped
    //@Post: If username or password is blank, a toast is popped
    //@Post: If neither of the above conditions are true, a new user is created with the given
    // username and password, the current user is set to be that user, and the client is moved
    // to the ChooseGameActivity
    
    public void register(String username, String password, String ipAddress, String authToken)
    {
        RegisterCommand newReg = new RegisterCommand(username,password,ipAddress, authToken);
        SendCommand sc2 = new SendCommand(newReg);
        sc2.start();
    }
    //@Pre: Client is in the ChooseGameActivity
    //@Pre: User is set in the main model
    //@Pre: "player" is the username of the current user
    //@Pre: gameID is the name of an existing pending game with less than five players
    //@Post: The client is moved into the LobbyActivity
    //@Post: All clients in the ChooseGameActivity on the same server  will see the count of
    // players in the game that is being joined increase by one.
    //@Post: The other users in the game that you're joining (who are also in the LobbyActivity)
    // will also see you join the game.
    //@Post: "Game" will be set in the MainModel to a non-null value.
    // Tells the server to add someone into a game
    
    public void joinGame(String player, String gameID)
    {
        JoinGameCommand newJoin =
                new JoinGameCommand(player,gameID);
        SendCommand sc2 = new SendCommand(newJoin);
        sc2.start();
    }
    //@Pre: gameID is the name of the game currently set in the MainModel
    //@Post: If there is only one player in the lobby, a toast is popped
    //@Post: If there is more than one player in the lobby, the following occur:
        // The game set in the MainModel changes to an active game
        // The activity changes to the MapActivity
        // The game is initiated with starting values
    // Tells the server to start a game
    
    public void startGame(String gameID)
    {
        StartGameCommand newStart = new StartGameCommand(gameID);
        SendCommand sc2 = new SendCommand(newStart);
        sc2.start();
    }
    //@Pre: The client is in the ChooseGameActivity
    //@Pre: The client's current game is null
    //@Pre: The player given represents the user set in the MainModel
    //@Pre: The gameID given is non-null
    //@Post: If the gameID is empty, a toast is popped
    //@Post: If the gameID is non-empty, the following occur:
        // A new pending game is created and set in the MainModel
        // The player given is added to that game
        // All clients in the ChooseGameActivity on the same server can see the new game
    // Tells the server to create a new game
    
    public void createGame(String player, String gameID)
    {
        CreateGameCommand newCreate =
                new CreateGameCommand(player, gameID);
        SendCommand sc2 = new SendCommand(newCreate);
        sc2.start();

    }
    //@Pre: There is an active game set in the MainModel
    //@Pre: The current activity is MapActivity
    //@Pre: "gameID" represents the name of the game set in the MainModel
    //@Pre: "message" is non-null
    //@Post: All users in the game associated with the gameID receive a chat message
    // Tells the server to send a chat message
    
    public void chat(ChatMessage message, String gameID)
    {
        ChatCommand newChat = new ChatCommand(message, gameID);
        SendCommand sc2 = new SendCommand(newChat);
        sc2.start();
    }
    //TODO: Not yet implemented
    //@Pre: "playerName" represents the username of the user set in MainModel
    //@Pre: It is the current user's turn
    //@Pre: "route" represents an unclaimed route
    //@Post: A dialog comes up with one of the following:
        // A message stating that you don't have enough cards to claim the route
        // A warning that claiming the route will take X rainbow cards
        // A choice between the two sides of the double-route selected
    // Tells the server to claim a route
    
    public void claimRoute(Route route, String playerName, boolean isSecond)
    {
        ClaimRouteCommand newClaim = new ClaimRouteCommand(route,playerName,isSecond);
        SendCommand sc2 = new SendCommand(newClaim);
        sc2.start();
    }
    //@Pre: The current activity is MapActivity
    //@Pre: An active game is set in the MainModel
    //@Pre: The current player has the provided destination card in his hand
    //@Post: The given card is removed from the current player's hand and placed at the bottom of
    // the DestinationDeck on the server
    // Tells the server to discard a card
    
    public void discardCard(DestinationCard card)
    {
        DiscardDestCommand newDiscard = new DiscardDestCommand(card);
        SendCommand sc2 = new SendCommand(newDiscard);
        sc2.start();
    }

    public void drawDest()
    {
        DrawDestCommand newDrawDest = new DrawDestCommand();
        SendCommand sc2 = new SendCommand(newDrawDest);
        sc2.start();
    }
    public void drawTrains(int pos1, int pos2)
    {
        DrawTrainsCommand newDraw = new DrawTrainsCommand(pos1,pos2);
        SendCommand sc2 = new SendCommand(newDraw);
        sc2.start();
    }

}
