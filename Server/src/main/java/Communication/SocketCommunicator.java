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
import Serialization.Serializer;


public class SocketCommunicator{
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    IServerSocket server;

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
        System.out.println("Sending Command to " + socket.getRemoteSocketAddress()+"\n");
        out.write(Serializer.serialize(wrapper)+",");
        out.flush();

    }


    public void readAndExecute()throws IOException{
        StringBuilder input = new StringBuilder();
        CommandWrapper[] commandWrappers = null;
//        System.out.println("Read from Socket");
        if(in.ready()){
            input.append(in.readLine());
        }
        if(input == null ||input.length() <=0 ){
//            System.out.println("Reading error " + input);
            return;
        }
        if(input.charAt(input.length()-1) == ','){
            input.setCharAt(input.length()-1,']');
            input = new StringBuilder("[" + input.toString());
            commandWrappers = Deserializer.deserializeWrappers(input.toString());
        }
        for(CommandWrapper wrapper : commandWrappers){
            try {
                StringBuilder type = new StringBuilder(wrapper.getType());
                type.insert(8,"Server");


                Command command = Deserializer.deserializeCommand(wrapper.getCommand(),type.toString());
                Object obj = command.execute();
                if(obj.getClass() == ErrorCommand.class){
                    command = (ErrorCommand) obj;
                }else{
                    command.addResults(obj);
                }
                System.out.println(command);
                server.sendToAll(command,this);
            }
            catch (ClassNotFoundException e){
                System.out.println("Command type is not found");
            }

        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SocketCommunicator)) return false;
        SocketCommunicator that = (SocketCommunicator) o;
        return Objects.equals(socket.getRemoteSocketAddress(), that.socket.getRemoteSocketAddress());
    }

    @Override
    public int hashCode() {

        return Objects.hash(socket);
    }
}
