package Communication;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import Command.Command;
import Command.CommandWrapper;

public class SendCommand extends Thread {
    private Gson json;
    private Command command;
    SendCommand(Command command)
    {
        json = new Gson();
        this.command = command;
    }
    @Override
    public void run() {
        System.out.println("Made it to send-command run");
        try {
            OutputStream server = CommandManager.get().getSocket().getOutputStream();
            CommandWrapper newWrapper =
                    new CommandWrapper(json.toJson(command), command.getClass().getName());
            writeString(json.toJson(newWrapper) + ",", server);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeString(String str, OutputStream os) {
        System.out.println("Made it to write-string");
        PrintWriter pw = new PrintWriter(os);
        pw.write(str + "\n");
        try {
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Message sent to server: " + str);
        //os.close();
    }
}
