import java.lang.reflect.Type;
import java.util.*;

public class ObservableSubscription extends Observable{
    HashMap<Type, Set<Observer>> subscriptions;
    Set<Observer> observers;


    public ObservableSubscription() {
        this.subscriptions = new HashMap<>();
        observers = new HashSet<>();
    }


    public synchronized void addObserver(Observer o, Type type) {
        if(subscriptions.containsKey(type)){
            subscriptions.get(type).add(o);
            return;
        }

        subscriptions.put(type,new HashSet<Observer>());
        subscriptions.get(type).add(o);
    }

    public synchronized void addObserver(Observer o) {
        observers.add(o);
    }

    public synchronized void deleteObserver(Observer o, Type type) {
        if(subscriptions.containsKey(type)){
            if(subscriptions.get(type).contains(o)){
                subscriptions.get(type).remove(o);
            }
        }
    }

    public synchronized void deleteObserver(Observer o) {
        for(Type currType: subscriptions.keySet()){
            if(subscriptions.get(currType).contains(o)){
                subscriptions.get(currType).remove(o);
            }
        }
        if(observers.contains(o)){
            observers.remove(o);
        }
    }


    public void notifyObservers() {
        for(Observer o:observers){
            o.update(this,null);
        }
    }


    public void notifyObservers(Object arg) {
        for(Observer o: subscriptions.get(arg.getClass())){
            o.update(null,arg);
        }
    }




    public synchronized int countObservers() {
        int count =0;
        count += observers.size();
        for(Type t : subscriptions.keySet()){
            count+= subscriptions.get(t).size();
        }
        return count;
    }
}
