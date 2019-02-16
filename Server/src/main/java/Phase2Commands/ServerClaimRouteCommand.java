package Phase2Commands;

import Command.Command;
import Phase2Models.Route;
import Phase2Services.ClaimRouteService;

public class ServerClaimRouteCommand implements Command {
    private Route route;
    private String playerName;
    private String ipAddress;

    @Override
    public Object execute() {
        ClaimRouteService claimRouteService = new ClaimRouteService();
        return claimRouteService.doService(route,playerName,ipAddress);
    }

    @Override
    public void addResults(Object obj) {

    }

    @Override
    public void setIpAddress(String ipAddress) {

    }
}
