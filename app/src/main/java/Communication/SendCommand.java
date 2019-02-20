package Communication;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import Command.Command;
import Command.CommandWrapper;


public class SendCommand extends AsyncTask<Command,Void,Void> {

    private Gson json;

    public SendCommand()
    {
        json = new Gson();
    }
    @Override
    protected Void doInBackground(Command... commands) {
        System.out.println("Made it to send-command do-in-background");
        try {
            OutputStream server = CommandManager.get().getSocket().getOutputStream();
            Command command = commands[0];
            CommandWrapper newWrapper =
                    new CommandWrapper(json.toJson(command), command.getClass().getName());
            writeString(json.toJson(newWrapper) + ",", server);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        System.out.print("Message sent to server: " + str + "\n");
        //os.close();
    }
}
