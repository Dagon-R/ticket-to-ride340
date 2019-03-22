package Phase3Services;

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
        Route route = (Route) obj[2];
        boolean isSecond = (boolean) obj[3];
    }
}
