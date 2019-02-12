package Communication;

import Command.*;
import Models.Player;
import Models.User;

public class ServerProxy {

    public ServerProxy() { }


    public void login(String username, String password, String ipAddress)
    {
        TheLoginCommand newLog = new TheLoginCommand(username,password,ipAddress);
        SendCommand2 sc2 = new SendCommand2(newLog);
        sc2.start();
    }
    public void register(String username,String password, String ipAddress)
    {
        RegisterCommand newReg = new RegisterCommand(username,password,ipAddress);
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
    public void createGame(User user, String gameID)
    {
        CreateGameCommand newCreate =
                new CreateGameCommand(user, gameID);
        SendCommand2 sc2 = new SendCommand2(newCreate);
        sc2.start();

    }

}
