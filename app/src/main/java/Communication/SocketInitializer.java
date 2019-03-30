package Communication;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import Models.MainModel;

// Sockets need to be created on a different thread, this is that thread
public class SocketInitializer extends AsyncTask<Object,Nullable,Socket> {

    private static final int TIME_OUT = 2000;
    private int isFinished = 0;
    public int isFinished() {return this.isFinished;}
    @Override
    // Takes in an IP Address and Port Number and returns a socket
    protected Socket doInBackground(Object... objects) {
        System.out.println("Made it to do in background!");
        if (objects.length != 2 || // Make sure there are two parameters and they're the right ones
                objects[0].getClass() != String.class ||
                objects[1].getClass() != Integer.class)
        {
            System.out.println("Socket Initializer initialized incorrectly");
            return null;
        }
        else {
            String ipAddress = (String) objects[0]; // Get the IP Address
            int portNumber = (Integer) objects[1]; // Get the port number
            try {
                // Create a new socket with the IP Address and port number
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ipAddress,portNumber),TIME_OUT);
                // Create a poller that's connected to this socket
                ClientPoller poller = ClientPoller.create(CommandManager.create(socket));
                poller.start(); // Start the poller
                this.isFinished = 1;
                return socket; // Return the newly created socket
            }
            catch (SocketTimeoutException e)
            {
                MainModel.get().setErrorMessage("Connection Timed Out");
                this.isFinished = -1;
                return null;
            }
            catch (IOException e) {
                e.printStackTrace(); // Print the error if one happens
                this.isFinished = -2;
                return null; // Return null in case of an error
            }
        }
    }
    public void resetFinished()
    {
        this.isFinished = 0;
    }
}
