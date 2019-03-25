package Phase3Services;

import Communication.ServerProxy;
import Models.Player;
import Models.MainModel;
import Phase2Models.Route;
import Services.Service;

public class ClaimRouteService implements Service {
    private MainModel model;

    public ClaimRouteService(){
        model = MainModel.get();
    }

    @Override
    //params: Route route, String playerName, boolean isSecond
    public void connectToProxy(Object... obj) {
        Route route = (Route) obj[0];
        String playerName = MainModel.get().getPlayer().getName();
        boolean isSecond = (boolean) obj[1];
        ServerProxy.get().claimRoute(route,playerName,isSecond);
    }

    @Override
    //params: String ipAddress, String playerName, Route route, boolean isSecond
    public void doService(Object... obj) {
        String ipAddress = (String) obj[0];
        String playerName = (String) obj[1];
        Player player = model.getGame().getActiveGame().getPlayer(playerName);
        Route route = (Route) obj[2];
        boolean isSecond = (boolean) obj[3];
        if (ipAddress.equals(model.getIPAddress()))
        {
            //TODO: Current Player Stuff
        }
        if (route.isDouble())
        {
            model.getGame().getActiveGame().setRouteOwner(route,player,isSecond);
        }
        else
        {
            model.getGame().getActiveGame().setRouteOwner(route,player);
        }

    }
}
