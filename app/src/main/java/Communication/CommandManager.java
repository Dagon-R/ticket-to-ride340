package Communication;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import Command.Command;
import Command.CommandWrapper;
import Command.InvalidCommandException;

public class CommandManager {
    private Socket server;
    private Queue<Command> queue;
    private Gson json;

    private static CommandManager inst;

    private CommandManager(Socket socket){
        json = new Gson();
        queue = new LinkedList<>();
        server = socket;
    }

    public Socket getSocket()
    {
        return server;
    }

    public static CommandManager get()
    {
        return inst;
    }
    static CommandManager create(Socket socket)
    {
        inst = new CommandManager(socket);
        return inst;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (server == null) {
            return;
        }
        try {
            server.getOutputStream().close();
            server.close();
        } catch (IOException e) {
            System.out.println("Server failed to close");
        }
    }

    public Command getCommand() {
        if (!queue.isEmpty()) {
            return queue.poll();
        } else {
            try {
                InputStream input = server.getInputStream();
                int bytesToRead = input.available();
                if (bytesToRead > 0) {
                    byte[] data = new byte[bytesToRead];
                    int readBytes = input.read(data);
                    System.out.println("Read " + readBytes + " bytes");
                    if (data[data.length - 1] != ',') {
                        throw new InvalidCommandException();
                    }
                    String dataJSON = "[" +
                            new String(Arrays.copyOfRange(data, 0, data.length - 1)) + "]";
                    System.out.println("Found following JSON: \n" + dataJSON);
                    CommandWrapper[] commandWrappers = json.fromJson(dataJSON, CommandWrapper[].class);
                    System.out.println("Successfully retrieved command wrappers");
                    for (CommandWrapper commandWrapper : commandWrappers) {
                        System.out.println(commandWrapper.getCommand() +" WRAPPERCOMMAND");
                        queue.add((Command) json.fromJson(commandWrapper.getCommand(),
                                Class.forName(commandWrapper.getType())));
//                        queue.add((Command) json.fromJson(commandWrapper.getCommand(),
//                                Class.forName(commandWrapper.getType())));
                        System.out.println("Added a command to the queue");
                    }
//{"username":"a","password":"a","valid":false,"ipAddress":"10.37.198.38","gameList":{"ServerActiveGames":{},"ServerPendingGames":{"a":{"players":["a"],"name":"a","id":"aa"}}},"authToken":"a1552005440442"}
                    return queue.poll();
                } else {
                    return null;
                }
            } catch (IOException e) {
                System.out.println("Unable to connect to socket to read command.");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("You may want to rename your classes...");
                e.printStackTrace();
            } catch (InvalidCommandException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public boolean isAvailable() throws IOException {
        return !queue.isEmpty() || server.getInputStream().available() > 0;
    }
    public String getOwnIP() {
        try{
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException e){
            System.out.println("HERE");
        }
        return null;
    }
}
