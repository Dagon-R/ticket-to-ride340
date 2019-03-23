package Phase3Services;

import Models.MainModel;
import Phase2Models.DestinationCard;
import Services.Service;

public class DrawDestService implements Service {
    private MainModel model;
    public DrawDestService() {model = MainModel.get();}

    //Param 1: String ipAddress
    //Param 2: DestinationCard card
    @Override
    public Object doService(Object... obj) {
        DestinationCard card = (DestinationCard) obj[0];
        return null;
    }
}
