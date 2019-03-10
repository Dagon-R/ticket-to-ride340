package Communication;

import Command.Command;
public interface IServerSocket {
    void sendToAll(Command command, SocketCommunicator socket);
    void addToBound(SocketCommunicator socketCommunicator,String game);
    void removeFromBound(SocketCommunicator socketCommunicator);
}
