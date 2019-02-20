package Phase2Models;

public class Store
{
    private TrainCardColor store[];
    private static final int STORE_SIZE = 5;
    public Store(TrainCardColor[] store) throws InvalidStoreLengthException
    {
        if (store.length == 5)
        {
            this.store = store;
        }
        else
        {
            throw new InvalidStoreLengthException();
        }
    }
    public TrainCardColor[] drawAt(int... indexes)
    {
        if (indexes.length > 2) {
            System.out.println("Cannot draw more than two cards");
            return null;
        }
        else if (indexes.length == 2)
        {
            if (indexes[0] >= STORE_SIZE || indexes[1] >= STORE_SIZE)
            {
                System.out.println("Cannot draw at an index greater than the store size");
                return null;
            }
            else if (store[indexes[0]] == TrainCardColor.RAINBOW ||
                    store[indexes[1]] == TrainCardColor.RAINBOW)
            {
                System.out.println("Cannot draw a rainbow with another card");
                return null;
            }
            else
            {
                return new TrainCardColor[]{store[indexes[0]],store[indexes[1]]};
            }
        }
        if (indexes.length == 1)
        {
            if (indexes[0] >= STORE_SIZE)
            {
                System.out.println("Cannot draw at an index greater than the store size");
                return null;
            }
            else
            {
                return new TrainCardColor[]{store[indexes[0]]};
            }
        }
        else {return new TrainCardColor[]{};}
    }
}
