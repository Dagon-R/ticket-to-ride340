package Command;

import java.util.Objects;

import Models.Player;
import Services.CreateGameService;
import Services.Service;

public class ServerCreateGameCommand implements Command {
    private Player player;
    private String gameID;
    private boolean valid;

    public ServerCreateGameCommand(Player player, String gameID, boolean valid) {
        this.player = player;
        this.gameID = gameID;
        this.valid = valid;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public void addResults(Object obj) {
        if(obj.getClass() != Boolean.class) return;
        Boolean val = (Boolean) obj;
        valid = val;
    }

    @Override
    public Object execute() {
        Service createGameService = new CreateGameService(player,gameID);
        return createGameService.doService();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerCreateGameCommand)) return false;
        ServerCreateGameCommand that = (ServerCreateGameCommand) o;
        return isValid() == that.isValid() &&
                Objects.equals(getPlayer(), that.getPlayer());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getPlayer(), isValid());
    }

    @Override
    public String toString() {
        return "ServerCreateGameCommand\n\t{" +
                "player=" + player +
                ", gameID='" + gameID + '\'' +
                ", valid=" + valid +
                '}';
    }
}
