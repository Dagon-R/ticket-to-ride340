package Phase2Commands;

import Command.Command;
import Phase2Services.ChatService;

public class ServerChatCommand implements Command {
    private String ipAddress;
    private String playerName;
    private String message;

    @Override
    public Object execute() {
        ChatService chatService = new ChatService();
        return chatService.doService(message,playerName,ipAddress);
    }

    @Override
    public void addResults(Object obj) {

    }

    @Override
    public void setIpAddress(String ipAddress) {

    }
}
