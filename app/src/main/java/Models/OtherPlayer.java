package Models;

import Phase2Models.DestinationCard;
import Phase2Models.TrainCardColor;

public class OtherPlayer extends APlayer {
    private int trainCards;
    private int destCards;

    public OtherPlayer(String name, PlayerColorEnum playerColor) {
        super(name, playerColor);
        this.trainCards = 0;
        this.destCards = 0;
    }

    @Override
    public int getTotalTrainCards() {
        return trainCards;
    }

    @Override
    public int getTotalDestinationCards() {
        return destCards;
    }

    @Override
    public void addToDestHand(DestinationCard card) {
        destCards++;
    }

    @Override
    public void addTrainCard(TrainCardColor card) {
        trainCards++;
    }
}
