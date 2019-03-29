package Phase3Services;

import Communication.ServerProxy;
import Models.ActiveGame;
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
        ActiveGame game = MainModel.get().getGame().getActiveGame();
        int pos1 = (int) obj[0];
        int pos2 = (int) obj[1];
        TrainCardColor color1 = (TrainCardColor) obj[2];
        TrainCardColor color2 = (TrainCardColor) obj[3];
        String playerID = (String) obj[4];
        Player player = game.getPlayer(playerID);
        TrainCardColor[] drawnCards = game.drawAt(pos1,pos2);
        if (pos1 != -1) {game.setBuffer(pos1,color1);}
        if (pos2 != -1) {game.setBuffer(pos2,color2);}
        for (TrainCardColor color : drawnCards) {player.addTrainCard(color);}
    }
}
