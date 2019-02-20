package Communication;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.net.Socket;

public class SocketInitializer extends AsyncTask<Object,Nullable,Socket> {

    @Override
    protected void onPreExecute() {
        System.out.println("Made it to pre-execute!");
    }

    @Override
    protected Socket doInBackground(Object... objects) {
        System.out.println("Made it to do in background!");
        if (objects.length != 2 ||
                objects[0].getClass() != String.class ||
                objects[1].getClass() != Integer.class)
        {
            System.out.println("Socket Initializer initialized incorrectly");
            return null;
        }
        else {
            String ipAddress = (String) objects[0];
            int portNumber = (Integer) objects[1];
            try {
                Socket socket = new Socket(ipAddress,portNumber);
                CommandManager manager = CommandManager.create(socket);
                ClientPoller poller = ClientPoller.create(manager);
                poller.start();
                return socket;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
