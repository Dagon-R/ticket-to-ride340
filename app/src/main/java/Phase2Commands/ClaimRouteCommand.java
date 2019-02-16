package Phase2Commands;

import Command.Command;
import Phase2Models.Route;
import Phase2Services.ClaimRouteService;

public class ClaimRouteCommand implements Command {
    private Route route;
    private String playerName;
    private String ipAddress;
    public ClaimRouteCommand(Route route, String name)
    {
        this.route = route;
        this.playerName = name;
    }
    @Override
    public void execute() {
        ClaimRouteService routeService = new ClaimRouteService();
        routeService.doService(route,playerName,ipAddress);
    }
}
