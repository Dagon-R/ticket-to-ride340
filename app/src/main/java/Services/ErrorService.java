package Services;

import Communication.CommandManager;
import Communication.ServerProxy;
import Models.MainModel;

public class ErrorService implements Service {
    private MainModel model;

    @Override
    public void connectToProxy(Object... obj) {
        model = MainModel.get();
    }

    @Override
    public void doService(Object... obj) {
        String message = (String) obj[0];
        String ipAddress = (String) obj[1];
        String authToken = (String) obj[2];

        model = MainModel.get();
        System.out.println("AUTHTOKEN: " + authToken);
        if(model.getAuthToken().equals(ipAddress) || model.getAuthToken().equals(authToken)){
            model.setErrorMessage(message);
        }
    }
}
