package Services;

import Communication.ServerProxy;
import Models.MainModel;

public class ErrorService implements Service {
    private ServerProxy sp;
    private MainModel model;

    @Override
    public void connectToProxy(Object... obj) {
        sp = new ServerProxy();
        model = MainModel.get();
    }

    @Override
    public void doService(Object... obj) {
        String message = (String) obj[0];
        String ipAddress = (String) obj[1];
        if(model.getIPAddress().equals(ipAddress)){
            model.setErrorMessage(message);
        }
    }
}
