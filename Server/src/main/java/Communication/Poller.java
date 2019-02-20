package Communication;

import Communication.IServerSocket;
import Communication.ServerSideSocket;

public class Poller extends Thread {
    ServerSideSocket server;
    public Poller(ServerSideSocket server){
        this.server = server;
    }

    @Override
    public void start() {
        super.run();
        while(true){
            server.poll();
        }
    }
}
