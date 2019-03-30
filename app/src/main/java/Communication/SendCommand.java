package Communication;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;

import Command.Command;
import Command.CommandWrapper;

//Instances of this class are created then executed in order to send commands to the server
// This is required so that writing to the server is on a separate thread
public class SendCommand extends Thread {
    private final Gson json; // Object used for JSON transformations
    private Command command; // The command that's getting ready to be sent
    private PrintWriter server;
    SendCommand(Command command)
    {
        json = new Gson(); // Prepare JSON for transformations

        this.command = command; // Set the command to be sent to the server
        try // Try to create a writer for the server
        { server = new PrintWriter(CommandManager.get().getSocket().getOutputStream()); }
        catch (IOException e)
        {
            System.out.println("Send Command failed to instantiate");
            e.printStackTrace(); // Print that a send command failed to be created
        }
    }
    @Override // Writes the JSON of the given command to the server
    public void run() {
        System.out.println("Made it to send-command run");
        CommandWrapper newWrapper = // Wraps the command in a command wrapper to send
                new CommandWrapper(json.toJson(command), command.getClass().getName());
        //Writes the wrapper with an added trailing comma in case multiple commands are sent
        writeString(json.toJson(newWrapper) + ",");
    }
    // A method for writing strings to the server
    private void writeString(String str) {
        System.out.println("Made it to write-string");
        // Write the given string to the output stream

        try {
            server.write(str + "\n");
            server.flush(); // Attempt to send the output stream to the server
        } catch (Exception e) {
            e.printStackTrace(); // Print a failure
        }
        // Print the method that was sent to the server
        System.out.println("Message sent to server: " + str);
    }
}
