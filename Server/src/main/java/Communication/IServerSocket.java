package Communication;

import Command.Command;
public interface IServerSocket {

    void send(Command command, SocketCommunicator socketCommunicator);
    void addToBound(SocketCommunicator socketCommunicator,String game);

}
