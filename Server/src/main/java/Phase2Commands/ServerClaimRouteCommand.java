package Phase2Commands;

import Command.Command;
import Phase2Models.Route;
import Phase2Services.ClaimRouteService;

public class ServerClaimRouteCommand implements Command {
    private Route route;
    private String playerName;
    private String ipAddress;
    private String gameID;

    @Override
    public Object execute(String gameID) {
        this.gameID = gameID;
        ClaimRouteService claimRouteService = new ClaimRouteService();
        return claimRouteService.doService(route,playerName,ipAddress);
    }

    @Override
    public void addResults(Object obj) {

    }

    @Override
    public String getGameID() {
        return gameID;
    }

    @Override
    public void setIpAddress(String ipAddress) {

    }
}
