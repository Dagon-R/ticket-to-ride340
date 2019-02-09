package Communication;

import Command.*;
import Models.Player;

public class ServerProxy {
    private static ServerProxy ourInstance = null;

    private static final int portNumber = 8080;

    public static ServerProxy get() {
        return ourInstance;
    }

    private CommandManager manager;

    private ServerProxy(String ipAddress)
    {
        manager = new CommandManager(ipAddress,portNumber);
    }

    public static ServerProxy create(String ipAddress) {
        ourInstance = new ServerProxy(ipAddress); return ourInstance;}

    public void login(String username, String password)
    {
        TheLoginCommand newLog = new TheLoginCommand(username,password,manager.getOwnIP());
        manager.sendCommand(newLog);
    }
    public void register(String username,String password)
    {
        RegisterCommand newReg = new RegisterCommand(username,password,manager.getOwnIP());
        manager.sendCommand(newReg);
    }

    public void joinGame(Player player, String gameID)
    {
        JoinGameCommand newJoin =
                new JoinGameCommand(player,gameID);
        manager.sendCommand(newJoin);
    }

    public void startGame(String gameID)
    {
        StartGameCommand newStart = new StartGameCommand(gameID);
        manager.sendCommand(newStart);
    }
    public void createGame(Player player, String gameID)
    {
        CreateGameCommand newCreate =
                new CreateGameCommand(player, gameID);
        manager.sendCommand(newCreate);

    }

}
