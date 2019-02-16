package Communication;

import Command.*;
import Models.Player;
import Models.User;
import Phase2Commands.ChatCommand;
import Phase2Commands.ClaimRouteCommand;
import Phase2Commands.DiscardCardCommand;
import Phase2Commands.StartGameCommand;
import Phase2Models.DestinationCard;
import Phase2Models.Route;
import Phase2Services.ClaimRouteService;

public class ServerProxy {

    public ServerProxy() { }


    public void login(String username, String password, String ipAddress, String authToken)
    {
        TheLoginCommand newLog = new TheLoginCommand(username,password,ipAddress, authToken);
        SendCommand2 sc2 = new SendCommand2(newLog);
        sc2.start();
    }
    public void register(String username,String password, String ipAddress, String authToken)
    {
        RegisterCommand newReg = new RegisterCommand(username,password,ipAddress, authToken);
        SendCommand2 sc2 = new SendCommand2(newReg);
        sc2.start();
    }

    public void joinGame(Player player, String gameID)
    {
        JoinGameCommand newJoin =
                new JoinGameCommand(player,gameID);
        SendCommand2 sc2 = new SendCommand2(newJoin);
        sc2.start();
    }

    public void startGame(String gameID)
    {
        StartGameCommand newStart = new StartGameCommand(gameID);
        SendCommand2 sc2 = new SendCommand2(newStart);
        sc2.start();
    }
    public void createGame(Player player, String gameID)
    {
        CreateGameCommand newCreate =
                new CreateGameCommand(player, gameID);
        SendCommand2 sc2 = new SendCommand2(newCreate);
        sc2.start();

    }
    public void chat(String username, String message)
    {
        ChatCommand newChat = new ChatCommand(username,message);
        SendCommand2 sc2 = new SendCommand2(newChat);
        sc2.start();
    }
    public void claimRoute(Route route, String playerName)
    {
        ClaimRouteCommand newClaim = new ClaimRouteCommand(route,playerName);
        SendCommand2 sc2 = new SendCommand2(newClaim);
        sc2.start();
    }
    public void discardCard(DestinationCard card)
    {
        DiscardCardCommand newDiscard = new DiscardCardCommand(card);
        SendCommand2 sc2 = new SendCommand2(newDiscard);
        sc2.start();
    }

}
