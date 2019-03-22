package Phase3Services;

import Services.Service;

public class EndGameService implements Service {
    @Override
    public void connectToProxy(Object... obj) {

    }

    // No Params
    @Override
    public void doService(Object... obj) {
        String ipAddress = (String) obj[0];
    }
}
