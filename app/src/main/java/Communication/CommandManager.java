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

// This is the class that handles JSON conversions and grabbing commands from the Server
public class CommandManager {
    private Socket server; // This is the actual Socket that is connected to the Server
    private Queue<Command> queue; // The queue of upcoming commands to execute
    private final Gson json; // This object handles JSON conversions

    private static CommandManager inst; // This is a singleton, it only has one instance

    // As this is a singleton, it has a private constructor. The socket is injected into this class.
    private CommandManager(Socket socket){
        json = new Gson();
        queue = new LinkedList<>();
        server = socket;
    }

    // Returns the socket (if one has been injected)
    public Socket getSocket()
    {
        return server;
    }

    // Returns the Command Manager (if one has been created)
    public static CommandManager get()
    {
        return inst;
    }

    // Creates a new Command Manager from a given Socket and returns it
    static CommandManager create(Socket socket)
    {
        return inst = new CommandManager(socket);
    }

    //TODO: Get this function working!
    @Override // Gets rid of the socket when it should be removed
    protected void finalize() throws Throwable {
        super.finalize(); // Call regular Java finalize functionality
        if (server == null) { // Check if the server is null
            return; // If so, do nothing
        }
        try { // Otherwise, try to finalize the server
            server.getOutputStream().close(); // Close the server's output stream
            server.close(); // Close the server itself
        } catch (IOException e) { // If the server fails to close, print a message
            System.out.println("Server failed to close");
        }
    }

    // Attempts to get the next command from the server. If there isn't one to grab,
    // this will return null
    public Command getCommand() {
        if (!queue.isEmpty()) { // Check if there's anything in the Command Queue
            return queue.poll(); // If so, grab the next thing in line and return it
        } else { // Otherwise
            try { // Try to get something from the Server
                InputStream input = server.getInputStream(); // Get ready to read from the Server
                int bytesToRead = input.available(); // Check how many bytes you can grab
                if (bytesToRead > 0) { // If the number is positive,
                    byte[] data = new byte[bytesToRead]; // Get an array ready to fit the command
                    int readBytes = input.read(data); // Grab the command with the array
                    // Print that a command was read
                    System.out.println("Read " + readBytes + " bytes");
                    if (data[data.length - 1] != ',') {
                        // If the last byte isn't a comma, something is wrong
                        throw new InvalidCommandException();
                    }
                    // Wrap the input in brackets and remove the final comma
                    String dataJSON = "[" +
                            new String(Arrays.copyOfRange(data, 0, data.length - 1)) + "]";
                    // Print the resulting string
                    System.out.println("Found following JSON: \n" + dataJSON);
                    // Turn whatever you read into an array of command wrappers
                    CommandWrapper[] commandWrappers = json.fromJson(dataJSON, CommandWrapper[].class);
                    // Print that you retrieved the command wrappers
                    System.out.println("Successfully retrieved command wrappers");
                    // Loop through the command wrappers that were found, and use the types attached
                    // to the wrappers to determine the type of Command to deserialize their inner
                    // JSON into. Add those commands to the queue
                    for (CommandWrapper commandWrapper : commandWrappers) {
                        queue.add((Command) json.fromJson(commandWrapper.getCommand(),
                                Class.forName(commandWrapper.getType())));
                        System.out.println("Added a command to the queue");
                    }
                    // Return the first command in the new array
                    return queue.poll();
                } else { // If there was nothing to read and the queue was empty, return null
                    return null;
                }
            } catch (IOException e) { // Print if the connection failed
                System.out.println("Unable to connect to socket to read command.");
                e.printStackTrace();
            } catch (ClassNotFoundException e) { // Print if the system didn't know how to determine
                // the "type" field of one of the Command Wrappers
                System.out.println("You may want to rename your classes...");
                e.printStackTrace();
            } catch (InvalidCommandException e) {
                // If what was read from the Server didn't end in a comma (it didn't finish writing
                // the new Command before it was polled)
                e.printStackTrace();
            }
            return null; // An exception occurred, return null
        }
    }

    // Check if there's anything for the manager to read from the server, whether it's already
    // something queued, or if it's something in the input stream
    public boolean isAvailable() throws IOException {
        return !queue.isEmpty() || server.getInputStream().available() > 0;
    }
    //TODO: Depreciate or update
    // Originally we tried desperately to find a way to get our own external facing IP address,
    // it was an effort that was not successful
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
