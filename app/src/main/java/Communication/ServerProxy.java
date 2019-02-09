package Communication;

import Command.*;
import Models.Player;
import Models.User;

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
        TheLoginCommand newLog = new TheLoginCommand(username,password);
        manager.sendCommand(newLog);
    }
    public void register(String username,String password)
    {
        RegisterCommand newReg = new RegisterCommand(username,password);
        manager.sendCommand(newReg);
    }

    public void joinGame(User user, String gameID)
    {
        JoinGameCommand newJoin =
                new JoinGameCommand(new Player(user.getName(),user.getColor(),user.getId()),gameID);
        manager.sendCommand(newJoin);
    }

    public void startGame(String gameID)
    {
        StartGameCommand newStart = new StartGameCommand(gameID);
        manager.sendCommand(newStart);
    }


}
