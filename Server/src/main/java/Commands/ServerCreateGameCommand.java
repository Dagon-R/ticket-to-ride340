package Commands;

import java.util.Objects;

import Services.Service;

public class ServerCreateGameCommand implements Command {
    private String gameName;

    public ServerCreateGameCommand() {
    }

    public ServerCreateGameCommand(String gameName) {
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
        Service createGameService = null;
        return createGameService.doService();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerCreateGameCommand)) return false;
        ServerCreateGameCommand that = (ServerCreateGameCommand) o;
        return Objects.equals(getGameName(), that.getGameName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getGameName());
    }
}
