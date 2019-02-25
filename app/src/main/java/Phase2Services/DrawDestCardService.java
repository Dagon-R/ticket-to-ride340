package Phase2Services;

import Communication.ServerProxy;
import Models.MainModel;
import Services.Service;

public class DrawDestCardService implements Service {
    private ServerProxy sp;
    private MainModel model;

    public DrawDestCardService(){
        sp = new ServerProxy();
        model = MainModel.get();
    }

    @Override
    //Params: String playerID
    public void connectToProxy(Object... obj) {

    }

    @Override
    //Params: String playerID, IDestCard card, String ipAddress
    public void doService(Object... obj) {


    }
}
