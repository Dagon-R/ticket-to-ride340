package Phase3Commands;

import Command.Command;
import Phase2Models.Route;
import Phase3Services.ClaimRouteService;

public class ClaimRouteCommand implements Command {
    private Route route;
    private String playerName;
    private volatile String ipAddress;
    private boolean isSecond;
    public ClaimRouteCommand(Route route, String name, boolean isSecond)
    {
        this.route = route;
        this.playerName = name;
        this.isSecond = isSecond;
    }
    @Override
    public void execute() {
        ClaimRouteService routeService = new ClaimRouteService();
        routeService.doService(ipAddress,playerName,route,isSecond);
    }
}
