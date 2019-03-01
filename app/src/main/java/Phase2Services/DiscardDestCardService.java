package Phase2Services;

import Communication.ServerProxy;
import Models.MainModel;
import Services.Service;

public class DiscardDestCardService implements Service {
    private ServerProxy sp;
    private MainModel model;

    public DiscardDestCardService(){
        sp = new ServerProxy();
        model = MainModel.get();
    }

    @Override
    //params: String playerID
    public void connectToProxy(Object... obj) {

    }

    @Override
    //params: String playerID, IDestCard card, String ipAddress
    public void doService(Object... obj) {

    }
}
