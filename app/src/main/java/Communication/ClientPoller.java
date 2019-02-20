package Communication;

import java.io.IOException;

import Command.Command;
import Models.MainModel;

public class ClientPoller extends Thread
{
    private static CommandManager manager;

    private static ClientPoller inst;

    private ClientPoller(CommandManager cmgr)
    {
        manager = cmgr;
    }

    static ClientPoller create(CommandManager cmgr)
    {
        inst = new ClientPoller(cmgr);
        return inst;
    }

    @Override
    public void run() {
        while (true) {
            try {
                while(!manager.isAvailable()){Thread.sleep(10); }
                Command command = manager.getCommand();
                if (command != null)
                { System.out.println("Executing: " + command.toString() + "\n");
                    command.execute(); }
            } catch (InterruptedException e) {
                MainModel.get().setErrorMessage("Why would you interrupt this thread?");
                e.printStackTrace();
                break;
            } catch (IOException e) {
                MainModel.get().setErrorMessage("Likely the server is no longer connected...");
                e.printStackTrace();
                break;
            }
        }
        MainModel.get().setErrorMessage("Failed Socket Connection");
    }
}
