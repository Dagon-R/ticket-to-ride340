package Commands;

import java.util.Objects;

import Services.Service;

public class ServerJoinGameCommand implements Command {
    private String gameName;
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
    public Object execute() {
        Service joinGameService = null;
        return joinGameService.doService();
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
