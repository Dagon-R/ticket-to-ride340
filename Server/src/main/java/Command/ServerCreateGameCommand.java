package Command;

import java.util.Objects;

import Models.GameList;
import Models.Player;
import Services.CreateGameService;
import Services.Service;

public class ServerCreateGameCommand implements Command {
    private Player player;
    private String gameID;

    private String ipAddress;
    private GameList gameList;


    public ServerCreateGameCommand(Player player, String gameID) {
        this.player = player;
        this.gameID = gameID;

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }



    @Override
    public void addResults(Object obj) {
        if(obj.getClass() != GameList.class) return;
        GameList gameList = (GameList) obj;
        this.gameList = gameList;
    }

    @Override
    public Object execute() {
        Service createGameService = new CreateGameService();
        return createGameService.doService(player,gameID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerCreateGameCommand)) return false;
        ServerCreateGameCommand that = (ServerCreateGameCommand) o;
        return Objects.equals(getPlayer(), that.getPlayer());
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPlayer());
    }



    @Override
    public String toString() {
        return "ServerCreateGameCommand{" +
                "player=" + player +
                ", gameID='" + gameID + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", gameList=" + gameList +
                '}';
    }
}
