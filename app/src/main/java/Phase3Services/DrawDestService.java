package Phase3Services;

import Models.MainModel;
import Phase2Models.DestinationCard;
import Services.Service;

public class DrawDestService implements Service {
    private MainModel model;
    public DrawDestService() {model = MainModel.get();}
    @Override
    public void connectToProxy(Object... obj) {

    }

    //Param 1: String ipAddress
    //Param 2: DestinationCard card
    @Override
    public void doService(Object... obj) {
        String ipAddress = (String) obj[0];
        DestinationCard card = (DestinationCard) obj[1];
    }
}
