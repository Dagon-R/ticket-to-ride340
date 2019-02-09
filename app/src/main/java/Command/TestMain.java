package Command;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class TestMain {
    private static String ipaddress = "10.24.202.102";
    private static int port = 8080;

    public static void main(String[] args) {
        Command command = new RegisterCommand("user", "pass");
        CommandManager manager = new CommandManager(ipaddress, port);
        manager.sendCommand(command);
        String nothing = "null";
        Command nextCommand = manager.getCommand();
        String something = "nothing";
    }
}
