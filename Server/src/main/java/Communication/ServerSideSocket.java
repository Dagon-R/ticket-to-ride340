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
import Phase2Commands.ServerChatCommand;

public class ServerSideSocket extends Thread implements IServerSocket{
    public static int totalSockets;
    static{
        totalSockets = 0;
    }
    Set<SocketCommunicator> pendingSockets;
    Set<SocketCommunicator> unboundSockets;
    Set<SocketCommunicator> allSockets;
    HashMap<String, Set<SocketCommunicator>> boundSockets;
    HashMap<SocketCommunicator,String> socketToGame;

    ServerSocket serverSocket;
    public ServerSideSocket(int port)throws IOException{
        serverSocket = new ServerSocket(port);
        unboundSockets = new CopyOnWriteArraySet<>();
        allSockets = new CopyOnWriteArraySet<>();
        pendingSockets = new HashSet<>();
        boundSockets = new HashMap<>();
        socketToGame = new HashMap<>();
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
//        if (command.getGameID() == null){
//            System.out.println("Command with no game id is trying to be sent to bound. Error in code");
//            return;
//        }
//
//        sendToGame(command,boundSockets.get(command.getGameID()));


    }

    private void sendToGame(Command command,String game){
        for(SocketCommunicator socketCommunicator : boundSockets.get(game)){
            socketCommunicator.send(command);
        }
    }

    public void send(Command command, SocketCommunicator socket){
        if(!socketToGame.containsKey(socket)){
            sendToUnbound(command);

            return;
        }
        if(!command.getClass().getSimpleName().equals(ServerChatCommand.class.getSimpleName())) {
            ServerChatCommand history = new ServerChatCommand();
            history.addResults(command);
            history.setGameID(socketToGame.get(socket));
            sendToGame(history, socketToGame.get(socket));
        }
        sendToGame(command,socketToGame.get(socket));


    }



    @Override
    public void addToBound(SocketCommunicator socketCommunicator,String game) {
        unboundSockets.remove(socketCommunicator);
        bindToGame  (socketCommunicator, game);
        bindToSocket(socketCommunicator, game);
    }

    private void bindToSocket(SocketCommunicator socketCommunicator, String game){
        socketToGame.put(socketCommunicator,game);
    }

    private void bindToGame(SocketCommunicator socketCommunicator, String game){
        if(boundSockets.containsKey(game)){
            boundSockets.get(game).add(socketCommunicator);
            return;
        }
        HashSet<SocketCommunicator> sockets = new HashSet<>();
        sockets.add(socketCommunicator);
        boundSockets.put(game,sockets);
    }


}
