package Command;

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
        while(true)
        {
            Command command = manager.getCommand();
            if (command != null)
            {
                command.execute();
            }
        }
    }
}
