package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;

import Phase2Models.DestinationCard;
import Phase2Models.TrainCardColor;

public class ThisPlayer extends Player {
    // New Fields
    private EnumMap<TrainCardColor,Integer> trainHand;
    private EnumSet<DestinationCard> destHand;

    public ThisPlayer(String name, PlayerColorEnum playerColor) {
        super(name, playerColor);
        this.trainHand = new EnumMap<>(TrainCardColor.class);
        this.destHand = EnumSet.noneOf(DestinationCard.class);
    }

    public void removeDestCard(DestinationCard card)
    {
        destHand.remove(card);
        setChanged();
        notifyObservers(this);
    }

    @Override
    public void addTrainCard(TrainCardColor card){
        Integer size = trainHand.get(card);
        size +=1;
        trainHand.put(card,size);
    }

    public int getCardColorCount(TrainCardColor color) { return trainHand.get(color); }

    public void setTrainHand(EnumMap<TrainCardColor,Integer> trainHand) {
        this.trainHand = trainHand;

        setChanged();
        notifyObservers("TrainCardColor");
    }

    public EnumSet<DestinationCard> getDestHand() {
        return destHand;
    }

    public void setDestHand(DestinationCard[] destHand) { //TODO TEST
        EnumSet<DestinationCard> temp = EnumSet.noneOf(DestinationCard.class);
        temp.addAll(Arrays.asList(destHand));
        this.destHand = temp;
        setChanged();
        notifyObservers("DestinationCard");
    }

    public void setDestHand(ArrayList<DestinationCard> destHand) { //TODO TEST
        EnumSet<DestinationCard> temp = EnumSet.noneOf(DestinationCard.class);
        temp.addAll(destHand);
        this.destHand = temp;
        setChanged();
        notifyObservers("DestinationCard");
    }

    @Override
    public void addToDestHand(DestinationCard card){
        this.destHand.add(card);
        setChanged();
        notifyObservers("DestinationCard");
    }

    @Override
    public int getTotalTrainCards() {
        int count = 0;
        for (int value : trainHand.values())
        {
            count += value;
        }
        return count;
    }

    @Override
    public int getTotalDestinationCards() {
        return destHand.size();
    }
}
