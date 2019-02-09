import java.io.IOException;

import Communication.Poller;
import Communication.ServerSideSocket;
import Models.MainModel;

import Services.LoginService;
import Services.RegisterService;

public class Server {

    public static void main(String[] args){
        try{
            RegisterService registerService = new RegisterService();
            registerService.doService("Bob","password");
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


