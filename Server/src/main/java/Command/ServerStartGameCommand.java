package Command;

import java.util.Objects;

import Services.Service;
import Services.StartGameService;

public class ServerStartGameCommand implements Command {
    private String gameID;
    private boolean starting;
    private String ipAddress;
    public ServerStartGameCommand() {
    }

    public ServerStartGameCommand(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public boolean isStarting() {
        return starting;
    }

    public void setStarting(boolean starting) {
        this.starting = starting;
    }



    @Override
    public void addResults(Object obj) {
        if(obj.getClass() != Boolean.class) return;
        Boolean start = (Boolean) obj;
        starting = start;
    }

    @Override
    public Object execute() {
        Service startGameService = new StartGameService();

        return startGameService.doService(gameID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerStartGameCommand)) return false;
        ServerStartGameCommand that = (ServerStartGameCommand) o;
        return Objects.equals(gameID, that.getGameID());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getGameID());
    }

    @Override
    public String toString() {
        return "ServerStartGameCommand\n\t{" +
                "gameID='" + gameID + '\'' +
                ", starting=" + starting +
                '}';
    }
}
