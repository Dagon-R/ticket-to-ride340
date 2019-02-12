package Communication;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.Socket;

public class SocketInitializer extends AsyncTask<Object,Void,Socket> {

    @Override
    protected Socket doInBackground(Object... objects) {
        if (objects.length != 2 ||
                objects[0].getClass() != String.class ||
                objects[1].getClass() != Integer.class)
        {
            System.out.print("Socket Initializer initialized incorrectly");
            return null;
        }
        else {
            String ipAddress = (String) objects[0];
            int portNumber = (Integer) objects[1];
            try {
                return new Socket(ipAddress,portNumber);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    protected void onPostExecute(Socket socket) {
        super.onPostExecute(socket);
        CommandManager manager = CommandManager.create(socket);
        ServerProxy.create(manager);
        ClientPoller poller = ClientPoller.create(manager);
        poller.doInBackground();
    }
}
