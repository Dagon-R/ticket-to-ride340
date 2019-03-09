package Phase2Services;

import Models.ActiveGame;
import Models.MainModel;
import Phase2Models.DestinationCard;

public class DiscardCardService implements Services.Service {
    @Override
    public Object doService(Object... obj) {
        DestinationCard destCard = (DestinationCard) obj[0];
        String gameId = (String) obj[1];
        ActiveGame game = MainModel.get().getGameList().getActiveGame(gameId);
        if (game != null)
        {
            game.discardDestCard(destCard);
            return true;
        }
        else
        {
            System.out.println("Game isn't active yet! (Or is null)");
            return false;
        }
    }
}
