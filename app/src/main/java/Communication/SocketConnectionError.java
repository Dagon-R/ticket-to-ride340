package Communication;

public class SocketConnectionError extends Exception {
    @Override
    public void printStackTrace() {
        System.out.print("Socket failed to connect to ip address and port number");
    }
}
