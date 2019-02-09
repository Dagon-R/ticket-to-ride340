package Command;


public class TestMain {

    public static void main(String[] args) {
        Command command = new RegisterCommand("user", "pass");
        int port = 8080;
        String ipaddress = "10.24.202.102";
        CommandManager manager = new CommandManager(ipaddress, port);
        manager.sendCommand(command);
        String nothing = "null";
        Command nextCommand = manager.getCommand();
        String something = "nothing";
    }
}
