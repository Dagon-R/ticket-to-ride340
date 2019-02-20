package Phase2Models;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class DestinationDeck {
    private ArrayBlockingQueue<DestinationCard> deck;

    public  DestinationDeck(){
        deck = new ArrayBlockingQueue<>(DestinationCard.values().length);
        deck.addAll(Arrays.asList(DestinationCard.values()));
    }
    public DestinationCard[] draw3()
    {
        DestinationCard result[] = new DestinationCard[3];
        if (deck.size() > 0) { result[0] = deck.poll();
        if (deck.size() > 0) {result[1] = deck.poll();
        if (deck.size() > 0) {result[2] = deck.poll();} } }
        return result;
    }
    public void discard(DestinationCard card)
    {
        deck.add(card);
    }
}
