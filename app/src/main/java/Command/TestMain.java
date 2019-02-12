package Command;


import Communication.ClientPoller;
import Communication.CommandManager;
import Communication.SocketConnectionError;
import Communication.SocketInitializer;
import Models.Player;
import Models.PlayerColorEnum;
import Models.User;

public class TestMain {

    public static void main(String[] args) throws InterruptedException {
        Command command = new RegisterCommand("user", "pass", "");
        Command command2 = new JoinGameCommand(new Player("name",PlayerColorEnum.BLACK),"Game1");
        Command command3 = new TheLoginCommand("user","pass", "");
        Command command4 = new StartGameCommand("theGame");
        Command command5 = new CreateGameCommand(new User("name","pass"), "Game2");
        int port = 8080;
        String ipaddress = "10.24.196.155";
        new SocketInitializer().execute(ipaddress,port);
        while(CommandManager.get() == null) {Thread.sleep(1);}
        CommandManager manager = CommandManager.get();


//        try{
//            CommandManager manager = new CommandManager(ipaddress, port);
            manager.sendCommand(command);
            manager.sendCommand(command2);
            manager.sendCommand(command3);
            manager.sendCommand(command4);
            manager.sendCommand(command5);
            String nothing = "null";
            Command nextCommand = manager.getCommand();
            Command nextCommand2 = manager.getCommand();
            Command nextCommand3 = manager.getCommand();
            Command nextCommand4 = manager.getCommand();
            Command nextCommand5 = manager.getCommand();
            String something = "nothing";
//        } catch(SocketConnectionError e){
//            System.out.println(e.getMessage());
//        }

    }
}
