package Phase3Services;

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
    //params: String playerID, Route route
    public void connectToProxy(Object... obj) {

    }

    @Override
    //params: String ipAddress, String playerName, Route route, boolean isSecond
    public void doService(Object... obj) {
        String ipAddress = (String) obj[0];
        String playerName = (String) obj[1];
        Player player = MainModel.get().getGame().getActiveGame().getPlayer(playerName);
        Route route = (Route) obj[2];
        boolean isSecond = (boolean) obj[3];
        if (ipAddress.equals(MainModel.get().getIPAddress()))
        {
            //TODO: Current Player Stuff
        }
        if (route.isDouble())
        {
            MainModel.get().getGame().getActiveGame().setRouteOwner(route,player,isSecond);
        }
        else
        {
            MainModel.get().getGame().getActiveGame().setRouteOwner(route,player);
        }

    }
}
