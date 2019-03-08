package Phase2Models;

import java.util.Observable;

public class Activities extends Observable {
    private ActivityTypes currentActivty;
    public Activities(ActivityTypes activty) {
        currentActivty = activty;
    }

    public void setCurrentActivty(ActivityTypes activty){
        currentActivty = activty;
        setChanged();
        notifyObservers();
    }
}
