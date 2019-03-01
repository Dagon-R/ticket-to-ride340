package Phase2Commands;

import Command.Command;
import Phase2Models.ChatMessage;
import Phase2Services.ChatService;

public class ServerChatCommand implements Command {
    private String ipAddress;
    private ChatMessage message;
    private String gameID;

    @Override
    public Object execute() {
        ChatService chatService = new ChatService();
        return chatService.doService(message, gameID, ipAddress);
    }

    @Override
    public void addResults(Object obj) {

    }

    @Override
    public void setIpAddress(String ipAddress) {

    }
}
