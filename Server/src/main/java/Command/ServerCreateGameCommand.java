package Command;

import java.util.Objects;

import Models.PendingGame;
import Services.CreateGameService;
import Services.Service;

public class ServerCreateGameCommand implements Command {
    private String player;
    private String gameID;

    private String ipAddress;
    //private GameList gameList;
    private volatile PendingGame pendingGame;


    public ServerCreateGameCommand(String player, String gameID) {
        this.player = player;
        this.gameID = gameID;

    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }


    public String getGameID() {
        return gameID;
    }

    @Override
    public void addResults(Object obj) {
//        if(obj == null) return;
//        if(obj.getClass() != GameList.class) return;
//        GameList gameList = (GameList) obj;
//        this.gameList = gameList;
        if(obj == null) return;
        if(obj.getClass() != PendingGame.class) return;
        this.pendingGame = (PendingGame) obj;
    }

    @Override
    public Object execute(String gameID) {
        Service createGameService = new CreateGameService();
        return createGameService.doService(player,this.gameID);
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
                //", gameList=" + gameList +
                '}';
    }
}
