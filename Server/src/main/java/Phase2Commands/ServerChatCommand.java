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
    public void addResults(Object obj) {
        Command command = (Command) obj;
        String commandName = command.getClass().getSimpleName();
        commandName = commandName.replace("Server","");
        commandName = commandName.replace("Command","");
        String name = String.join(" ",commandName.split("(?=\\p{Upper})"));
        message = new ChatMessage("Console",name);

    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID){
        this.gameID = gameID;
    }

    @Override
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
