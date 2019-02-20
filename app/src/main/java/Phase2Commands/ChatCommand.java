package Phase2Commands;

import Command.Command;
import Phase2Services.ChatService;

public class ChatCommand implements Command {
    private String ipAddress;
    private String playerName;
    private String message;
    @Override
    public void execute() {
        ChatService chatter  = new ChatService();
        chatter.doService(playerName,message,ipAddress);
    }
    public ChatCommand(String playerName,String message)
    {
        this.playerName = playerName;
        this.message = message;
    }
}
