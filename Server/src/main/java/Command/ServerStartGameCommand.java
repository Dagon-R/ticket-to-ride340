package Command;

import java.util.Objects;

import Services.Service;
import Services.StartGameService;

public class ServerStartGameCommand implements Command {
    private String gameName;
    private boolean starting;
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
    public void addResults(Object obj) {
        if(obj.getClass() != Boolean.class) return;
        Boolean start = (Boolean) obj;
        starting = start;
    }

    @Override
    public Object execute() {
        Service startGameService = new StartGameService();

        return startGameService.doService(gameName);
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
