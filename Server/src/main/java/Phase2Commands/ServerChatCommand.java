package Phase2Commands;

import Command.Command;
import Phase2Models.ChatMessage;

public class ServerChatCommand implements Command {
    private String ipAddress;
    private ChatMessage message;
    private String gameID;

    @Override
    public Object execute(String gameID) {
        return true;
    }

    @Override
    public void addResults(Object obj) { }
    @Override
    public String getGameID() {
        return gameID;
    }
    @Override
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
