package Phase3Services;

import Communication.ServerProxy;
import Models.MainModel;
import Models.Player;
import Phase2Models.TrainCardColor;
import Services.Service;

public class DrawTrainsService implements Service {
    @Override
    //int pos1, int pos2
    public void connectToProxy(Object... obj) {
        String playerID = MainModel.get().getPlayer().getName();
        int pos1 = (int) obj[1];
        int pos2 = (int) obj[2];
        ServerProxy.get().drawTrains(playerID,pos1,pos2);
    }

    @Override
    public void doService(Object... obj) {
        TrainCardColor color1 = (TrainCardColor) obj[0];
        TrainCardColor color2 = (TrainCardColor) obj[1];
        String playerID = (String) obj[2];
        Player player = MainModel.get().getGame().getActiveGame().getPlayer(playerID);
        player.addTrainCard(color1);
        player.addTrainCard(color2);
    }
}
