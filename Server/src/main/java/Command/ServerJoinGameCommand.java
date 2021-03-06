package Command;

import java.util.Objects;

import Models.GameList;
import Models.PendingGame;
import Services.JoinGameService;
import Services.Service;

public class ServerJoinGameCommand implements Command {
//    private Player player;
    private String player;
    private String gameID;
    //private boolean joined;
    private String ipAddress;
    //private GameList gameList;
    private PendingGame pendingGame;
    public ServerJoinGameCommand() {
    }

    public ServerJoinGameCommand(String player, String gameID/*, boolean joined*/) {
        this.player = player;
        this.gameID = gameID;
        //this.joined = joined;
    }



    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameName) {
        this.gameID = gameName;
    }

    @Override
    public void addResults(Object obj) {
        if(obj == null) return;
        if(obj.getClass() != PendingGame.class) return;
        this.pendingGame = (PendingGame) obj;
    }

    @Override
    public Object execute(String gameID) {
        Service joinGameService = new JoinGameService();
        return joinGameService.doService(player,this.gameID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerJoinGameCommand)) return false;
        ServerJoinGameCommand that = (ServerJoinGameCommand) o;
        return Objects.equals(gameID, that.getGameID());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getGameID());
    }

    @Override
    public String toString() {
        return "ServerJoinGameCommand{" +
                "player=" + player +
                ", gameID='" + gameID + '\'' +
                //", joined=" + joined +
                ", ipAddress='" + ipAddress + '\'' +
                ", pendingGame=" + pendingGame +
                '}';
    }
}
