package Commands;

import java.util.Objects;

import Services.Service;

public class ServerStartGameCommand implements Command {
    private String gameName;
    public ServerStartGameCommand() {
    }

    public ServerStartGameCommand(String gameName) {
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
        Service startGameService = null;

        return startGameService.doService();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerStartGameCommand)) return false;
        ServerStartGameCommand that = (ServerStartGameCommand) o;
        return Objects.equals(getGameName(), that.getGameName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getGameName());
    }
}
