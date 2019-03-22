package Phase2Services;

import Communication.ServerProxy;
import Models.MainModel;
import Phase2Models.DestinationCard;
import Services.Service;

public class DiscardDestService implements Service {
    private MainModel model;

    public DiscardDestService(){
        model = MainModel.get();
    }

    @Override
    //params: DestinationCard card
    public void connectToProxy(Object... obj) {
        ServerProxy.get().discardCard((DestinationCard) obj[0]);
    }

    @Override
    //params: DestCard card, String ipAddress
    public void doService(Object... obj) {
        DestinationCard destinationCard = (DestinationCard) obj[0];
        String ipAddress = (String) obj[1];
        if (ipAddress.equals(model.getIPAddress()))
        {
            model.discardCard(destinationCard);
        }
    }
}
