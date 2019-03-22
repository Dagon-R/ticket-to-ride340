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
    //params: String playerName, Route route, boolean isSecond
    public Object doService(Object... obj) {
        String playerName = (String) obj[0];
        Route route = (Route) obj[1];
        boolean isSecond = (boolean) obj[2];
    }
}
