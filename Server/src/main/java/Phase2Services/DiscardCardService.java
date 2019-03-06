package Phase2Services;

import Models.ActiveGame;
import Models.IGame;
import Models.MainModel;
import Phase2Models.DestinationCard;

public class DiscardCardService implements Services.Service {
    @Override
    public Object doService(Object... obj) {
        DestinationCard destCard = (DestinationCard) obj[0];
        String gameId = (String) obj[1];
        IGame game = MainModel.get().getGameList().get(gameId);
        if (game.getClass() == ActiveGame.class)
        {
            ((ActiveGame)MainModel.get().getGameList().get(gameId)).discardDestCard(destCard);
            return true;
        }
        else
        {
            System.out.println("Game isn't active yet! (Or is null)");
            return false;
        }
    }
}
