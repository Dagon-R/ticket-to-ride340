package Phase2Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;

public class TrainDeck {
    private static final int DECK_COLOR_SIZE = 12;

    private ArrayList<TrainCardColor> deck = new ArrayList<>();

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

        Collections.shuffle(deck);

    }
    public TrainCardColor[] draw2()
    {
        TrainCardColor[] result = new TrainCardColor[2];
        for(int i = 0; i < 2; i++) {
            if (deck.size() > 0) {
                result[i] = deck.get(0);
                deck.remove(0);
            }
        }
        return result;
    }
    public TrainCardColor[] draw4()
    {
        TrainCardColor[] result = new TrainCardColor[4];
        for(int i = 0; i < 4; i++) {
            if (deck.size() > 0) {
                result[i] = deck.get(0);
                deck.remove(0);
            }
        }
        return result;
    }

    public TrainCardColor[] drawStore(){
        TrainCardColor[] result = new TrainCardColor[5];
        for(int i = 0; i < 5; i++) {
            if (deck.size() > 0) {
                result[i] = deck.get(0);
                deck.remove(0);
            }
        }
        return result;
    }
}
