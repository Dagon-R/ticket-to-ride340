package Communication;

import Command.*;
import Models.Player;
import Phase2Commands.ChatCommand;
import Phase2Commands.ClaimRouteCommand;
import Phase2Commands.DiscardCardCommand;
import Phase2Commands.StartGameCommand;
import Phase2Models.ChatMessage;
import Phase2Models.DestinationCard;
import Phase2Models.Route;

public class ServerProxy {

    public ServerProxy() { }

    public void login(String username, String password, String ipAddress, String authToken)
    {
        TheLoginCommand newLog = new TheLoginCommand(username,password,ipAddress, authToken);
        SendCommand sc2 = new SendCommand(newLog);
        sc2.start();
    }
    public void register(String username,String password, String ipAddress, String authToken)
    {
        RegisterCommand newReg = new RegisterCommand(username,password,ipAddress, authToken);
        SendCommand sc2 = new SendCommand(newReg);
        sc2.start();
    }

    public void joinGame(Player player, String gameID)
    {
        JoinGameCommand newJoin =
                new JoinGameCommand(player,gameID);
        SendCommand sc2 = new SendCommand(newJoin);
        sc2.start();
    }

    public void startGame(String gameID)
    {
        StartGameCommand newStart = new StartGameCommand(gameID);
        SendCommand sc2 = new SendCommand(newStart);
        sc2.start();
    }
    public void createGame(Player player, String gameID)
    {
        CreateGameCommand newCreate =
                new CreateGameCommand(player, gameID);
        SendCommand sc2 = new SendCommand(newCreate);
        sc2.start();

    }
    public void chat(ChatMessage message, String gameID)
    {
        ChatCommand newChat = new ChatCommand(message, gameID);
        SendCommand sc2 = new SendCommand(newChat);
        sc2.start();
    }
    public void claimRoute(Route route, String playerName)
    {
        ClaimRouteCommand newClaim = new ClaimRouteCommand(route,playerName);
        SendCommand sc2 = new SendCommand(newClaim);
        sc2.start();
    }
    public void discardCard(DestinationCard card)
    {
        DiscardCardCommand newDiscard = new DiscardCardCommand(card);
        SendCommand sc2 = new SendCommand(newDiscard);
        sc2.start();
    }

}
