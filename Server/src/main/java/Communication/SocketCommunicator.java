package Communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

import Command.Command;
import Command.ErrorCommand;
import Command.CommandWrapper;
import Serialization.Deserializer;
import Command.ServerCreateGameCommand;
import Command.ServerJoinGameCommand;
import Serialization.Serializer;


public class SocketCommunicator{
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    IServerSocket server;
    String gameID;

    public SocketCommunicator(Socket socket, IServerSocket server)throws IOException{
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(),true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.server =server;
    }

    public String getIP(){
//        socket.get
        return socket.getRemoteSocketAddress().toString();
    }


    public void send(Command command){
        String name = String.join("",command.getClass().getName().split("Server"));
        CommandWrapper wrapper = new CommandWrapper(Serializer.serialize(command), name);
        System.out.println("Sending Command to " + socket.getInetAddress().getHostAddress()+"\n");
        out.write(Serializer.serialize(wrapper)+",");
        out.flush();

    }


    public void readAndExecute()throws IOException{
        StringBuilder input = new StringBuilder();
        CommandWrapper[] commandWrappers = read();
        if(commandWrappers == null) return;
//        System.out.println("Read from Socket");
        execute(commandWrappers);


    }

    private void execute(CommandWrapper[] commandWrappers){
        for(CommandWrapper wrapper : commandWrappers){
            try {
                StringBuilder type = new StringBuilder(wrapper.getType());
                int index = wrapper.getType().indexOf('.');
                type.insert(index+1,"Server");

//                System.out.println(ServerStartGameCommand.class +" " + type);
                Command command = Deserializer.deserializeCommand(wrapper.getCommand(),type.toString());
                System.out.println(command);
                Object obj =command.execute(gameID);

                if(obj.getClass() == ErrorCommand.class){
                    command = (Command)obj;
                }

//                System.out.println(obj);
                command.addResults(obj);
                command.setIpAddress(socket.getInetAddress().getHostAddress());


                checkGame(command);
                server.send(command,this);
            }
            catch (ClassNotFoundException e){
                System.out.println("Command type: '"+wrapper.getType()+"' is not found");
            }

        }
    }

    private void checkGame(Command command){
//        System.out.println("Attempting to bind socket to game. If not desired, Comment out method call");

        String commandType = command.getClass().getName();
        String createGame = ServerCreateGameCommand.class.getName();
        String joinGame = ServerJoinGameCommand.class.getName();
        if(commandType.equals(createGame)){
            ServerCreateGameCommand c = (ServerCreateGameCommand) command;
            gameID= c.getGameID();
            server.addToBound(this,gameID);
        }

        if(commandType.equals(joinGame)){
            ServerJoinGameCommand c = (ServerJoinGameCommand) command;
            gameID = c.getGameID();
            server.addToBound(this,gameID);
        }

    }

    private CommandWrapper[] read()throws IOException{
        StringBuilder input = new StringBuilder();

        if(in.ready()){
            input.append(in.readLine());
        }
        if(input.length() <=0 ){
//            System.out.println("Reading error " + input);
            return null;
        }
        if(input.charAt(input.length()-1) == ','){
            input.setCharAt(input.length()-1,']');
            input = new StringBuilder("[" + input.toString());
            return Deserializer.deserializeWrappers(input.toString());
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SocketCommunicator)) return false;
        SocketCommunicator that = (SocketCommunicator) o;
        if(!socket.getInetAddress().getHostAddress().equals(that.socket.getInetAddress().getHostAddress()))return false;
        if(gameID == null && that.gameID == null) return true;
        if(gameID != null && that.gameID == null) return false;
        if(gameID == null && that.gameID != null) return false;
        return gameID.equals(that.getGameID());
    }

    public String getGameID() {
        return gameID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(socket);
    }
}
