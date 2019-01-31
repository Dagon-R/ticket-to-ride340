package Communication;

import java.util.Date;

public class Poller extends Thread {
    ServerSideSocket serverSideSocket;
    public Poller(ServerSideSocket serverSideSocket){
        this.serverSideSocket = serverSideSocket;

    }

    @Override
    public void run(){

        while(true){

            serverSideSocket.poll();
        }
    }

}
