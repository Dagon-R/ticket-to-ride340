package Communication;

import java.io.IOException;

import Command.Command;
import Models.MainModel;

// This poller starts once a connection to a server has been initiated (likely by the response of
// a login / register command) and continuously polls the server for commands to execute
public class ClientPoller extends Thread
{
    // The Command Manager manages the socket and has the required method to attempt to
    // acquire a command from the server
    private static CommandManager manager;

    // This class is a singleton, this is the single static instance of this class
    private static ClientPoller inst;

    // As this is a singleton, here is it's private constructor
    private ClientPoller(CommandManager cmgr)
    {
        manager = cmgr;
    }

    // This is how the ClientPoller is initially initialized, this is distinguished from 'get'
    // so that it can be referred to without providing a CommandManager in the future.
    static ClientPoller create(CommandManager cmgr)
    {
        // If it already exists, return it
        if (inst != null) {return inst;}
        else
        { // Otherwise, make a new one and return it
            return (inst = new ClientPoller(cmgr));
        }
    }

    // Returns the static instance of this class
    static ClientPoller get() { return inst; }

    @Override // Continuously polls the server for commands to execute
    public void run() {
        while (true) { // Run continuously (this isn't on the main thread)
            try { // While there's nothing to do, do nothing
                while(!manager.isAvailable()){Thread.sleep(10); }
                // When there's something to do, grab that something
                Command command = manager.getCommand();
                // If it truly is something, execute it
                if (command != null) {
                    // Also, tell the console what you're executing
                    System.out.println("Executing: " + command.toString() + "\n");
                    command.execute(); // Execute the command
                }
            } catch (InterruptedException e) { // This catches if the poller is interrupted
                MainModel.get().setErrorMessage("Why would you interrupt this thread?");
                e.printStackTrace();
                break;
            } catch (IOException e) { // This catches if the connection breaks
                MainModel.get().setErrorMessage("Likely the server is no longer connected...");
                e.printStackTrace();
                break;
            }
        }
        // You only get here if one of the catch blocks is invoked
        MainModel.get().setErrorMessage("Failed Socket Connection");
    }
}
