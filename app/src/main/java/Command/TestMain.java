package Command;


import Models.Player;
import Models.PlayerColorEnum;

public class TestMain {

    public static void main(String[] args) {
        Command command = new RegisterCommand("user", "pass");
        Command command2 = new JoinGameCommand(new Player("name",PlayerColorEnum.BLACK,"123"),"Game1");
        int port = 8080;
        String ipaddress = "192.168.1.200";
        CommandManager manager = new CommandManager(ipaddress, port);
        manager.sendCommand(command);
        manager.sendCommand(command2);
        String nothing = "null";
        Command nextCommand = manager.getCommand();
        Command nextCommand2 = manager.getCommand();
        String something = "nothing";
    }
}
