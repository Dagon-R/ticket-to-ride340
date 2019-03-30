package Phase2Commands;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Objects;

import Command.Command;
import Models.ReturnObjects.StartGameReturn;
import Phase2Models.DestinationCard;
import Phase2Models.TrainCardColor;
import Services.Service;
import Phase2Services.StartGameService;

public class ServerStartGameCommand implements Command {
    private volatile TrainCardColor[] store;
    private volatile HashMap<String, DestinationCard[]> drawnCards;
    private volatile HashMap<String, Integer[]> drawnTrains;
    private String gameID;
    private volatile String ipAddress;

    public ServerStartGameCommand() {
    }

    public ServerStartGameCommand(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    @Override
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public void addResults(Object obj) {
        if(obj == null) return;
        if(obj.getClass() != StartGameReturn.class) return;
        StartGameReturn returnVal = (StartGameReturn) obj;
        this.store = returnVal.getStore().getStore();
        this.drawnCards = returnVal.getDrawnDestCards();
        this.drawnTrains = returnVal.getDrawnTrains();
        this.gameID = returnVal.getGameID();
    }

    @Override
    public Object execute(String gameID) {
        Service startGameService = new StartGameService();

        return startGameService.doService(gameID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerStartGameCommand)) return false;
        ServerStartGameCommand that = (ServerStartGameCommand) o;
        return Objects.equals(gameID, that.getGameID());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getGameID());
    }

    @Override
    public String toString() {
        return "ServerStartGameCommand{" +
                "gameID='" + gameID + '\'' +
                ", store=" + store +
                ", ipAddress='" + ipAddress + '\'' +
                ", trainCards=" + drawnTrains +
                ", destCards=" + drawnCards +
                '}';
    }
}
