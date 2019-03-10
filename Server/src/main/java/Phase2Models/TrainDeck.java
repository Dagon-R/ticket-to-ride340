package Phase2Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;

public class TrainDeck {
    private static final int DECK_COLOR_SIZE = 12;

    private ArrayBlockingQueue<TrainCardColor> deck =
            new ArrayBlockingQueue<>((TrainCardColor.values().length * DECK_COLOR_SIZE) + 2);

    public TrainDeck() {
        for (TrainCardColor color : TrainCardColor.values())
        {
            for (int i = 0; i < DECK_COLOR_SIZE; i++)
            {
                deck.add(color);
            }
        }
        deck.add(TrainCardColor.RAINBOW);
        deck.add(TrainCardColor.RAINBOW);

        ArrayList<TrainCardColor> list = new ArrayList(deck);
        Collections.shuffle(list);
        deck = new ArrayBlockingQueue<>((TrainCardColor.values().length * DECK_COLOR_SIZE) + 2, true, deck);

    }
    public TrainCardColor[] draw2()
    {
        TrainCardColor[] result = new TrainCardColor[2];
        if (deck.size() > 0) {result[0] = deck.poll();
        if (deck.size() > 0) {result[1] = deck.poll();}}
        return result;
    }
    public TrainCardColor[] draw4()
    {
        TrainCardColor[] result = new TrainCardColor[4];
        if (deck.size() > 0) {result[0] = deck.poll();
        if (deck.size() > 0) {result[1] = deck.poll();}
        if (deck.size() > 0) {result[2] = deck.poll();}
        if (deck.size() > 0) {result[3] = deck.poll();}}
        return result;
    }

    public TrainCardColor[] drawStore(){
        TrainCardColor[] result = new TrainCardColor[5];
        if (deck.size() > 0) {result[0] = deck.poll();}
        if (deck.size() > 0) {result[1] = deck.poll();}
        if (deck.size() > 0) {result[2] = deck.poll();}
        if (deck.size() > 0) {result[3] = deck.poll();}
        if (deck.size() > 0) {result[4] = deck.poll();}
        return result;
    }
}
