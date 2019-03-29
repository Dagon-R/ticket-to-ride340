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
    //params: String playerID, Route route, bool isSecond
    public void connectToProxy(Object... obj) {
        String playerID = (String) obj[0];
        Route route = (Route) obj[1];
        Boolean isSecond = (Boolean) obj[3];

        ServerProxy.get().claimRoute(route, playerID, isSecond);
    }

    @Override
    //params: String ipAddress, String playerName, Route route, boolean isSecond
    public void doService(Object... obj) {
        String ipAddress = (String) obj[0];
        String playerName = (String) obj[1];
        Player player = model.getGame().getActiveGame().getPlayer(playerName);
        Route route = (Route) obj[2];
        boolean isSecond = (boolean) obj[3];
//        if (ipAddress.equals(MainModel.get().getIPAddress()))
//        {
//            //nothing specific for player
//        }
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
