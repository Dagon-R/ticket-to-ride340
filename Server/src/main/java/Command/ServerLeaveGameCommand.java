package Command;

import java.util.Objects;

import Models.GameList;
import Models.Player;
import Services.Service;

public class ServerLeaveGameCommand implements Command {
    Player player;
    String gameID;
    GameList gameList;
    String ipAddress;

    @Override
    public Object execute() {
        Service leaveGameService = null;
        return leaveGameService.doService(player,gameID);
    }

    @Override
    public void addResults(Object obj) {
        if(obj == null) return;
        if(obj.getClass() != GameList.class) return;
        GameList gameList = (GameList) obj;
        this.gameList = gameList;

    }

    public String getGameID() {
        return null;
    }
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerLeaveGameCommand)) return false;
        ServerLeaveGameCommand that = (ServerLeaveGameCommand) o;
        return Objects.equals(player, that.player) &&
                Objects.equals(gameID, that.gameID) &&
                Objects.equals(gameList, that.gameList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(player, gameID, gameList);
    }

    @Override
    public String toString() {
        return "ServerLeaveGameCommand{" +
                "player=" + player +
                ", gameID='" + gameID + '\'' +
                ", gameList=" + gameList +
                '}';
    }
}
