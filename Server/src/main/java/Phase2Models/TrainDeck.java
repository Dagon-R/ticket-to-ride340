package Phase2Models;

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
    }
    public TrainCardColor[] draw2()
    {
        TrainCardColor[] result = new TrainCardColor[2];
        if (deck.size() > 0) {result[0] = deck.poll();
        if (deck.size() > 0) {result[1] = deck.poll();}}
        return result;
    }
}
