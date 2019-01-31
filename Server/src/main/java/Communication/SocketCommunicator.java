package Communication;

import java.io.IOException;
import java.net.Socket;

import Commands.Command;

public class SocketCommunicator{
    Socket socket;
    public SocketCommunicator(Socket socket){
        this.socket = socket;
    }
    public void send(Command command){
        System.out.println("Serialize, and write to stream");
    }


    public void readAndExecute()throws IOException{
        System.out.println("Read from socket stream, deserialize, and execute");

    }




}
