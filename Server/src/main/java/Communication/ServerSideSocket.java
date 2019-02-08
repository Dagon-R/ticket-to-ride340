package Communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Commands.Command;

public class ServerSideSocket extends Thread implements IServerSocket{
    public static int totalSockets;
    static{
        totalSockets = 0;
    }
    Set<SocketCommunicator> unboundSockets;
    Set<SocketCommunicator> allSockets;
    HashMap<String, SocketCommunicator> boundSockets;
    ServerSocket serverSocket;
    public ServerSideSocket(int port)throws IOException{
        serverSocket = new ServerSocket(port);
        unboundSockets = new HashSet<>();
        allSockets = new HashSet<>();
    }

    public void poll(){
        if(getTotalSockets() <=0) return;
        for(SocketCommunicator socket : allSockets){
            try{
                socket.readAndExecute();
            }
            catch(IOException e){
                System.out.println("Socket Disconnected");
                totalSockets--;
            }

        }
    }

    @Override
    public void run() {
        super.run();

        while(true){
            Socket socket = null;

            try{
                socket = serverSocket.accept();
                System.out.println("New Socket");
                if(socket == null) continue;
//                socket.getRe
                addSocket(socket);
                totalSockets++;
            }
            catch(IOException e){
                System.out.println("Socket Failed to Connect");
                System.out.println(e.getMessage());
            }
        }

    }

    private void addSocket(Socket socket)throws IOException{
        SocketCommunicator socketCom = new SocketCommunicator(socket,this);
        allSockets.add(socketCom);
        unboundSockets.add(socketCom);

    }

    public static int getTotalSockets(){
        return totalSockets;
    }

    @Override
    public void sendToAll(Command command) {

    }
}
