package Phase2Services;

import Communication.ServerProxy;
import Models.MainModel;
import Phase2Models.DestinationCard;
import Services.Service;

public class DiscardDestCardService implements Service {
    private MainModel model;

    public DiscardDestCardService(){
        model = MainModel.get();
    }

    @Override
    //params: String playerID
    public void connectToProxy(Object... obj) {
        ServerProxy.get().discardCard((DestinationCard) obj[0]);
    }

    @Override
    //params: DestCard card, String ipAddress
    public void doService(Object... obj) {
        DestinationCard destinationCard = (DestinationCard) obj[0];
        String ipAddress = (String) obj[1];
        if (ipAddress.equals(MainModel.get().getIPAddress()))
        {
            MainModel.get().discardCard(destinationCard);
        }
    }
}
