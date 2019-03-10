package Models;

import java.util.Observable;

public class Game extends Observable {
    private ActiveGame activeGame;
    private PendingGame pendingGame;

    public Game() {
    }

    public void setActiveGame(ActiveGame activeGame) {
        this.activeGame = activeGame;
        setChanged();
        notifyObservers(activeGame);
    }

    public void setPendingGame(PendingGame pendingGame) {
        this.pendingGame = pendingGame;
        setChanged();
        notifyObservers(pendingGame);
    }

    public void addToPendingGame(String player){
        this.pendingGame.addPlayer(player);
        setChanged();
        notifyObservers(pendingGame);
    }

    public ActiveGame getActiveGame() {
        return activeGame;
    }

    public PendingGame getPendingGame() {
        return pendingGame;
    }
}
