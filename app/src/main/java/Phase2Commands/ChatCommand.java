package Phase2Commands;

import Command.Command;
import Phase2Models.ChatMessage;
import Phase2Services.ChatService;

public class ChatCommand implements Command {
    private ChatMessage message;
    private volatile String ipAddress;
    @Override
    public void execute() {
        ChatService chatter  = new ChatService();
        chatter.doService(message,ipAddress);
    }
    public ChatCommand(ChatMessage message)
    {
        this.message = message;
    }
}
