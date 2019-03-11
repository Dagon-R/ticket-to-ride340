package Models;


import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Observable;

import Phase2Models.DestinationCard;
import Phase2Models.TrainCardColor;

public class Player extends Observable implements Comparable<Player>{
	static String TAG = "Player";
	//The color that represents this player
	private PlayerColorEnum playerColor;
	//The name of this player
	private String name;


	// New Fields
	private EnumMap<TrainCardColor,Integer> trainHand;
	private EnumSet<DestinationCard> destHand; //TODO: might have to override some methods bc enumsets are weird
	private int score;
	private int piecesLeft;


	public Player(String name, PlayerColorEnum playerColor){
		this.name = name;
		piecesLeft = 45;
		this.playerColor = playerColor;
		score =0;
	}

	public void removeDestCard(DestinationCard card)
	{
		destHand.remove(card);
		setChanged();
		notifyObservers(this);
	}

	public void addTrainCard(TrainCardColor card){
		Integer size = trainHand.get(card);
		size +=1;
		trainHand.put(card,size);
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
	public int compareTo(Player o) {
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

	public int getCardColorCount(TrainCardColor color) { return trainHand.get(color); }

	public void setTrainHand(EnumMap<TrainCardColor,Integer> trainHand) {
		this.trainHand = trainHand;

		setChanged();
		notifyObservers("TrainCardColor");
	}

	public EnumSet<DestinationCard> getDestHand() {
		return destHand;
	}

	public void setDestHand(DestinationCard[] destHand) { //TODO TEST
		EnumSet<DestinationCard> temp = EnumSet.noneOf(DestinationCard.class);
		temp.addAll(Arrays.asList(destHand));
		this.destHand = temp;
		setChanged();
		notifyObservers("DestinationCard");
	}

	public void setDestHand(ArrayList<DestinationCard> destHand) { //TODO TEST
		EnumSet<DestinationCard> temp = EnumSet.noneOf(DestinationCard.class);
		temp.addAll(destHand);
		this.destHand = temp;
		setChanged();
		notifyObservers("DestinationCard");
	}

	public void addToDestHand(DestinationCard card){
		this.destHand.add(card);
		setChanged();
		notifyObservers("DestinationCard");
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

	public int getTotalTrainCards(){
		int total = 0;
		total += trainHand.get(TrainCardColor.BLUE);
		total += trainHand.get(TrainCardColor.RED);
		total += trainHand.get(TrainCardColor.GREEN);
		total += trainHand.get(TrainCardColor.ORANGE);
		total += trainHand.get(TrainCardColor.PURPLE);
		total += trainHand.get(TrainCardColor.YELLOW);
		total += trainHand.get(TrainCardColor.BLACK);
		total += trainHand.get(TrainCardColor.WHITE);
		total += trainHand.get(TrainCardColor.RAINBOW);
		return total;
	}

	public int getTotalDestinationCards(){
		return destHand.size();
	}
}