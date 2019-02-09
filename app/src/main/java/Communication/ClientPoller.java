package Communication;

import java.io.IOException;

import Command.Command;

public class ClientPoller extends Thread
{
    static private final int portNumber = 8080;
    private CommandManager manager;
    public ClientPoller(CommandManager manager)
    {
        this.manager = manager;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                while(!manager.isAvailable()){Thread.sleep(10); }
                Command command = manager.getCommand();
                if (command != null)
                { System.out.println("Executing: " + command.toString() + "\n");
                command.execute(); }
            } catch (InterruptedException e) {
                System.out.print("Why would you interrupt this thread?\n");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.print("Likely the server is no longer connected...\n");
                e.printStackTrace();
                break;
            }
        }
    }
}
