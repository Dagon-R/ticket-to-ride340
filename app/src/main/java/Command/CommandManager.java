package Command;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CommandManager {
    private Socket server;
    private Queue<Command> queue;
    private Gson json;

    public CommandManager(String ipAddress, int portNumber) {
        try {
            json = new Gson();
            server = new Socket(ipAddress, portNumber);
            queue = new LinkedList<>();
        } catch (IOException e) {
            System.out.print("Couldn't connect to server.\n");
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (server == null) {
            return;
        }
        try {
            server.getOutputStream().close();
            server.close();
        } catch (IOException e) {
            System.out.print("Server failed to close");
        }
    }

    public Command getCommand() {
        if (!queue.isEmpty()) {
            return queue.poll();
        } else {
            try {
                InputStream input = server.getInputStream();
                int bytesToRead = input.available();
                if (bytesToRead > 0) {
                    byte[] data = new byte[bytesToRead];
                    int readBytes = input.read(data);
                    System.out.print("Read " + readBytes + " bytes\n");
                    if (data[data.length - 1] != ',') {
                        throw new InvalidCommandException();
                    }
                    String dataJSON = "[" +
                            new String(Arrays.copyOfRange(data, 0, data.length - 1)) + "]";
                    System.out.print("Found following JSON: \n" + dataJSON + "\n");
                    CommandWrapper[] commandWrappers = json.fromJson(dataJSON, CommandWrapper[].class);
                    System.out.print("Successfully retrieved command wrappers");
                    for (CommandWrapper commandWrapper : commandWrappers) {
                        queue.add((Command) json.fromJson(commandWrapper.getCommand(),
                                Class.forName(commandWrapper.getType())));
                        System.out.print("Added a command to the queue");
                    }
                    return queue.poll();
                } else {
                    return null;
                }
            } catch (IOException e) {
                System.out.print("Unable to connect to socket to read command.\n");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.print("You may want to rename your classes...");
                e.printStackTrace();
            } catch (InvalidCommandException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void sendCommand(Command command) {
        CommandWrapper newWrapper =
                new CommandWrapper(json.toJson(command), command.getClass().getName());
        try {
            writeString(json.toJson(newWrapper) + ",", server.getOutputStream());
        } catch (IOException e) {
            System.out.print("Unable to connect to socket to write command.\n");
            e.printStackTrace();
        }
    }

    private void writeString(String str, OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        pw.write(str + "\n");
        pw.flush();
        System.out.print("Message sent to server: " + str + "\n");
        //os.close();
    }

    boolean isAvailable() throws IOException {
        return server.getInputStream().available() > 0;
    }
}
