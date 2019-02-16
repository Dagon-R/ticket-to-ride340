package Phase2Models;

public class Store
{
    private static Store inst;

    private static final int STORE_SIZE = 5;

    private TrainCardColor store[];

    private Store(TrainCardColor[] store)
    {
        this.store = store;
    }

    public static Store create(TrainCardColor[] store) throws InvalidStoreLengthException
    {
        if (store.length == STORE_SIZE)
        {
            inst = new Store(store);
        }
        else
        {
            throw new InvalidStoreLengthException();
        }
        return null;
    }
    public static Store get()
    {
        return inst;
    }
}
