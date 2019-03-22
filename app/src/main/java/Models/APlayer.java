package Models;

import java.util.Observable;

import Phase2Models.DestinationCard;
import Phase2Models.TrainCardColor;

public abstract class APlayer extends Observable implements Comparable<APlayer>{

    private int score;
    private int piecesLeft;
    private String name;
    private PlayerColorEnum playerColor;

    public APlayer(String name, PlayerColorEnum playerColor){
        this.name = name;
        piecesLeft = 45;
        this.playerColor = playerColor;
        score =0;
    }
    public void incrementScore(int points){
        score+=points;
        setChanged();
        notifyObservers(this);
    }
    public PlayerColorEnum getColor(){
        return playerColor;
    }

    public String getName(){
        return name;
    }

    private void setColor(PlayerColorEnum input){
        this.playerColor = input;
        setChanged();
        notifyObservers(this);
    }

    private void setName(String input){
        this.name = input;
        setChanged();
        notifyObservers(this);
    }
    @Override
    public int compareTo(APlayer o) {
        return getColor().compareTo(o.getColor());
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
    public PlayerColorEnum getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColorEnum playerColor) {
        this.playerColor = playerColor;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        setChanged();
        notifyObservers(this);
    }
    public int getPiecesLeft() {
        return piecesLeft;
    }

    public void decrementPiecesLeft(int dec){
        this.piecesLeft -=dec;
        setChanged();
        notifyObservers(this);
    }

    public void setPiecesLeft(int piecesLeft) {
        this.piecesLeft = piecesLeft;
        setChanged();
        notifyObservers(this);
    }
    public abstract int getTotalTrainCards();

    public abstract int getTotalDestinationCards();

    public abstract void addToDestHand(DestinationCard card);

    public abstract void addTrainCard(TrainCardColor card);

}
