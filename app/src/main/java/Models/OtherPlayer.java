package Models;

import java.util.EnumMap;

import Phase2Models.DestinationCard;
import Phase2Models.TrainCardColor;

public class OtherPlayer extends Player {
    private int trainCards;
    private int destCards;

    OtherPlayer(String name, PlayerColorEnum playerColor) {
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

    @Override
    public void setTrainHand(EnumMap<TrainCardColor, Integer> trainCardColorMap) {
        int count = 0;
        for (int num : trainCardColorMap.values())
        {
            count += num;
        }
        this.trainCards = count;
    }

    @Override
    public void setDestHand(DestinationCard[] destinationCards) {
        this.destCards = destinationCards.length;
    }
}
