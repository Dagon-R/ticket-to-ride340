package Phase2Services;

import Communication.ServerProxy;
import Models.MainModel;
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
    //params: String playerID, Route route, String ipAddress
    public void doService(Object... obj) {

    }
}
