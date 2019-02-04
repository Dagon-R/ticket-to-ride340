package Communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Commands.Command;
import Commands.CommandWrapper;
import Serialization.Deserializer;
import Serialization.Serializer;
import sun.security.krb5.internal.crypto.Des;

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


    public void send(Command command){
        CommandWrapper wrapper = new CommandWrapper(Serializer.serialize(command), command.getClass().getName());

        out.write(Serializer.serialize(wrapper)+",");

    }


    public void readAndExecute()throws IOException{
        StringBuilder input = null;
        CommandWrapper[] commandWrappers = null;
        if(in.ready()){
            input.append(in.readLine());
        }
        System.out.println(input);
//        if(input.charAt(input.length()-1) == ','){
//            input.setCharAt(input.length()-1,']');
//            input = new StringBuilder("[" + input.toString());
//            commandWrappers = Deserializer.deserializeWrappers(input.toString());
//        }
//        for(CommandWrapper wrapper : commandWrappers){
//            try {
//                Command command = Deserializer.deserializeCommand(wrapper.getCommand(),"Server" + wrapper.getType());
//                command.execute();
//                server.sendToAll(command);
////                socket.
//            }
//            catch (ClassNotFoundException e){
//                System.out.println("Command type is not found");
//            }

//        }






    }




}
