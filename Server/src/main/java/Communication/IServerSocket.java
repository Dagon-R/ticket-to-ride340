package Communication;
import Commands.Command;
public interface IServerSocket {
    void sendToAll(Command command);
}
