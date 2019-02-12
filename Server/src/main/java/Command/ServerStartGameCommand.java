package Command;

import java.util.Objects;

import Models.GameList;
import Services.Service;
import Services.StartGameService;

public class ServerStartGameCommand implements Command {
    private String gameID;
    private boolean starting;
    private String ipAddress;
    private GameList gameList;
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
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public void addResults(Object obj) {
        if(obj == null) return;
        if(obj.getClass() != GameList.class) return;
        GameList gameList = (GameList) obj;
        this.gameList = gameList;
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
        return "ServerStartGameCommand{" +
                "gameID='" + gameID + '\'' +
                ", starting=" + starting +
                ", ipAddress='" + ipAddress + '\'' +
                ", gameList=" + gameList +
                '}';
    }
}
