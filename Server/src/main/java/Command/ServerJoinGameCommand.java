package Command;

import java.util.Objects;

import Services.JoinGameService;
import Services.Service;

public class ServerJoinGameCommand implements Command {
//    private Player player;
    private String gameName;
    private boolean joined;
    public ServerJoinGameCommand() {
    }

    public ServerJoinGameCommand(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public void addResults(Object obj) {
        if(obj.getClass() != Boolean.class) return;
        Boolean val = (Boolean) obj;
        joined = val;
    }

    @Override
    public Object execute() {
        Service joinGameService = new JoinGameService();
        return joinGameService.doService(gameName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerJoinGameCommand)) return false;
        ServerJoinGameCommand that = (ServerJoinGameCommand) o;
        return Objects.equals(getGameName(), that.getGameName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getGameName());
    }
}
