package Communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import Command.Command;

public class ServerSideSocket extends Thread implements IServerSocket{
    public static int totalSockets;
    static{
        totalSockets = 0;
    }
    Set<SocketCommunicator> pendingSockets;
    Set<SocketCommunicator> unboundSockets;
    Set<SocketCommunicator> allSockets;
    HashMap<String, Set<SocketCommunicator>> boundSockets;

    ServerSocket serverSocket;
    boolean accessing;
    public ServerSideSocket(int port)throws IOException{
        serverSocket = new ServerSocket(port);
        unboundSockets = new CopyOnWriteArraySet<>();
        allSockets = new CopyOnWriteArraySet<>();
        pendingSockets = new HashSet<>();
        boundSockets = new HashMap<>();
    }

    public void poll()throws ConcurrentModificationException {
//        if(getTotalSockets() <=0) return;

        for(SocketCommunicator socket : allSockets){
            try{
//                System.out.println(socket);
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
                System.out.println("New Socket " + socket.getInetAddress().getHostAddress());
                if(socket == null) continue;
//                socket.getRe

                asyncAddSocket(socket);
                totalSockets++;
            }
            catch(IOException e){
                System.out.println("Socket Failed to Connect");
                System.out.println(e.getMessage());
            }
        }

    }


    private void asyncAddSocket(Socket socket)throws IOException{
        SocketCommunicator socketCom = new SocketCommunicator(socket,this);
        if(!allSockets.add(socketCom)){
            allSockets.remove(socketCom);
            allSockets.add(socketCom);
        }
        if(!unboundSockets.add(socketCom)){
            unboundSockets.remove(socketCom);
            unboundSockets.add(socketCom);
        }



    }

    public static int getTotalSockets(){
        return totalSockets;
    }

    private void sendToUnbound(Command command){
        for(SocketCommunicator socketCommunicator : unboundSockets){
            socketCommunicator.send(command);
        }
    }

    private void sendToBound(Command command, SocketCommunicator socket){
        if (command.getGameID() == null){
            System.out.println("Command with no game id is trying to be sent to bound. Error in code");
            return;
        }

        sendToGame(command,boundSockets.get(command.getGameID()));


    }

    private void sendToGame(Command command, Set<SocketCommunicator> sockets){
        for(SocketCommunicator socketCommunicator : sockets){
            socketCommunicator.send(command);
        }
    }
    @Override
    public void sendToAll(Command command, SocketCommunicator socket) {
        if(unboundSockets.contains(socket)){
            sendToUnbound(command);

        }
        else{
            sendToBound(command,socket);
        }
    }


    @Override
    public void addToBound(SocketCommunicator socketCommunicator,String game) {
        allSockets.remove(socketCommunicator);
        if(boundSockets.containsKey(game)){
            boundSockets.get(game).add(socketCommunicator);
            return;
        }
        HashSet<SocketCommunicator> sockets = new HashSet<>();
        sockets.add(socketCommunicator);
        boundSockets.put(game,sockets);

    }

    @Override
    public void removeFromBound(SocketCommunicator socketCommunicator) {
        if(allSockets.contains(socketCommunicator)) return;
        allSockets.add(socketCommunicator);
        for(String game : boundSockets.keySet()){
            if(boundSockets.get(game).contains(socketCommunicator)){
                boundSockets.get(game).remove(socketCommunicator);
                if(boundSockets.get(game).size() <=0){
                    boundSockets.remove(game);
                }
            }
        }

    }
}
