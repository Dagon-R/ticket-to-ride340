package Phase3Commands;

import Command.Command;
import Phase2Models.Route;
import Phase3Services.ClaimRouteService;

public class ServerClaimRouteCommand implements Command {
    private Route route;
    private String playerName;
    private volatile String ipAddress;
    private boolean isSecond;
    public ServerClaimRouteCommand(Route route, String name, boolean isSecond)
    {
        this.route = route;
        this.playerName = name;
        this.isSecond = isSecond;
    }
    @Override
    public Object execute(String gameID) {
        ClaimRouteService routeService = new ClaimRouteService();
        return routeService.doService(ipAddress,playerName,route,isSecond);
    }

    @Override
    public void addResults(Object obj) {

    }

    @Override
    public void setIpAddress(String ipAddress) {

    }
}
