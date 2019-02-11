import java.io.IOException;

import Communication.Poller;
import Communication.ServerSideSocket;
import Models.MainModel;

import Models.Player;
import Models.PlayerColorEnum;
import Services.CreateGameService;
import Services.JoinGameService;
import Services.LoginService;
import Services.RegisterService;
import Services.StartGameService;

public class Server {

    public static void main(String[] args){
        try{
            RegisterService registerService = new RegisterService();
            System.out.println(registerService.doService("Bob", "password"));
//            CreateGameService createGameService = new CreateGameService();
//            createGameService.doService(new Player("Bob", PlayerColorEnum.BLACK, "df"),"Game");
//            JoinGameService joinGameService = new JoinGameService();
//            System.out.println(joinGameService.doService(new Player("d", PlayerColorEnum.BLACK, "df"),"Game"));
//            System.out.println(joinGameService.doService(new Player("b", PlayerColorEnum.BLACK, "df"),"Game"));
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


