import java.io.IOException;
import java.net.Socket;

import Command.ServerRegisterCommand;

public class MockClient {
    public static void main(String[] args)throws IOException {
        Socket socket = new Socket();
//        socket.connect(new InetSocketAddress("127.0.0.1", 8080));
        System.out.println((new ServerRegisterCommand().getClass().getName()).split("",-1));
    }
}
