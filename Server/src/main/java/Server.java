import java.io.IOException;

import Communication.Poller;
import Communication.ServerSideSocket;

//import Services.LeaveGameService;


public class Server {

    public static void main(String[] args){
        try{
//            RegisterService registerService = new RegisterService();
//            registerService.doService("Bob", "password");
//            CreateGameService createGameService = new CreateGameService();
//            createGameService.doService(new Player("Bob", PlayerColorEnum.BLACK, "df"),"Game");
//            JoinGameService joinGameService = new JoinGameService();
//            joinGameService.doService(new Player("d", PlayerColorEnum.BLACK, "df"),"Game");
//            joinGameService.doService(new Player("b", PlayerColorEnum.BLACK, "df"),"Game");
//            LeaveGameService leaveGameService = new LeaveGameService();
//            try{
//                leaveGameService.doService("");
//            }
//            catch (AssertionError e){
//                System.out.println(e.getMessage());
//            }
//            StartGameService startGameService = new StartGameService();
//            System.out.println(startGameService.doService("Game"));
            //            CreateGameService serv = new CreateGameService("");
//            System.out.println(registerService.doService("Bob", "password"));

            ServerSideSocket serverSideSocket = new ServerSideSocket(8080);
            serverSideSocket.start();
            Poller poller = new Poller(serverSideSocket);
            poller.start();



        }
        catch(IOException e){
            System.out.println("Server Socket Failed to start");
            System.out.println(e.getMessage());
        }


    }
}


