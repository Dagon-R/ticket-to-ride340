package Models;

import java.util.Observable;

public class ErrorMessage extends Observable {
    private String error;

    public ErrorMessage() {
    }

    public void setError(String error){
        System.out.println("ERROR MESSAGE BEING SET");
        this.error = error;
        setChanged();
        notifyObservers();
    }

    public String getError() {
        return error;
    }
}
