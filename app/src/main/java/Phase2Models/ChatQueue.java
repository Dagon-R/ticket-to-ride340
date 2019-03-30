package Phase2Models;

import java.util.ArrayList;
import java.util.Observable;

public class ChatQueue extends Observable {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private ArrayList<ChatMessage> chatQueue = new ArrayList<>();
    public void add(ChatMessage message){
        chatQueue.add(message);
        setChanged();
        notifyObservers(this);
    }

    public ArrayList<ChatMessage> getQueue(){
        return chatQueue;
    }
}
