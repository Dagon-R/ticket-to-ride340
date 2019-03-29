package Phase3Services;

import Communication.ServerProxy;
import Services.Service;

public class EndGameService implements Service {

    //not doing? all on client?
    @Override
    public void connectToProxy(Object... obj) {
    }

    // No Params
    @Override
    public void doService(Object... obj) {
        String ipAddress = (String) obj[0];
        //probs set some model var to trigger dialog
    }
}
