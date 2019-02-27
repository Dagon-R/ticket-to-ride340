package Communication;

import Command.Command;
public interface IServerSocket {
    void sendToAll(Command command, SocketCommunicator socket);
}
