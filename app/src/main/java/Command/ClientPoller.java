package Command;

import java.io.IOException;

public class ClientPoller extends Thread
{
    static private final int portNumber = 8080;
    private CommandManager manager;
    ClientPoller(String ipAddress)
    {
        manager = new CommandManager(ipAddress,portNumber);
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                while(!manager.isAvailable()){Thread.sleep(10); }
                Command command = manager.getCommand();
                if (command != null) { command.execute(); }
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
