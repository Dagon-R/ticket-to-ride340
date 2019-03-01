package Phase2Commands;

import Command.Command;
import Phase2Models.ChatMessage;
import Phase2Services.ChatService;

public class ChatCommand implements Command {
    private ChatMessage message;
    private String gameID;
    private volatile String ipAddress;
    @Override
    public void execute() {
        ChatService chatter  = new ChatService();
        chatter.doService(message, gameID, ipAddress);
    }
    public ChatCommand(ChatMessage message, String gameID)
    {
        this.message = message;
        this.gameID = gameID;
    }
}
