import java.io.IOException;

import Communication.Poller;
import Communication.ServerSideSocket;

public class Server {

    public static void main(String[] args){
        try{
            ServerSideSocket serverSideSocket = new ServerSideSocket(8080);
            Poller poller = new Poller(serverSideSocket);
            poller.start();
        }
        catch(IOException e){
            System.out.println("Server Socket Failed to start");
            System.out.println(e.getMessage());
        }


    }
}


