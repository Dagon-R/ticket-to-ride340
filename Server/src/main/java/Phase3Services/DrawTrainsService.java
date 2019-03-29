package Phase3Services;

import Models.MainModel;
import Phase2Models.TrainCardColor;
import Phase2Models.TrainDeck;
import Services.Service;

public class DrawTrainsService implements Service {

    //Params: int pos1, int pos2
    //Indexes 0-4 are the store, 5 is the deck, and -1 is a skipped draw
    @Override
    public Object doService(Object... obj) {
        String gameID = (String) obj[0];
        int pos1 = (int) obj[1];
        int pos2 = (int) obj[2];
        TrainDeck deck = MainModel.get().getGameList().getActiveGame(gameID).getTrainDeck();
        if (pos1 == -1 || pos2 == -1)
        {
            return new TrainCardColor[]{deck.draw()};
        }
        return MainModel.get().getGameList().getActiveGame(gameID).getTrainDeck().draw2();
    }
}
