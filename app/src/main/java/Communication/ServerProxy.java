package Communication;

public class ServerProxy {
    private static final ServerProxy ourInstance = new ServerProxy();

    public static ServerProxy get() {
        return ourInstance;
    }

    private ServerProxy() {
    }

}
