package Models;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import Phase2Models.DestinationCard;
import Phase2Models.TrainCardColor;

public class Player {
	//The color that represents this player
	private PlayerColorEnum playerColor;
	//The name of this player
	private String name;


	// New Fields
	private ArrayList<TrainCardColor> trainHand;
	private EnumSet<DestinationCard> destHand; //TODO: might have to override some methods bc enumsets are weird
	private int score;
	private int piecesLeft;


	public Player(String name, PlayerColorEnum playerColor){
		this.name = name;
		this.playerColor = playerColor;
	}

	public void removeDestCard(DestinationCard card)
	{
		destHand.remove(card);
	}
	
	public PlayerColorEnum getColor(){
		return playerColor;
	}
	
	public String getName(){
		return name;
	}

	private void setColor(PlayerColorEnum input){
		this.playerColor = input;
	}
	
	private void setName(String input){
		this.name = input;
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

	public ArrayList<TrainCardColor> getTrainHand() {
		return trainHand;
	}

	public void setTrainHand(ArrayList<TrainCardColor> trainHand) {
		this.trainHand = trainHand;
	}

	public EnumSet<DestinationCard> getDestHand() {
		return destHand;
	}

	public void setDestHand(DestinationCard[] destHand) { //TODO TEST
		EnumSet<DestinationCard> temp = EnumSet.noneOf(DestinationCard.class);
		temp.addAll(Arrays.asList(destHand));
		this.destHand = temp;
	}

	public void addToDestHand(DestinationCard card){
		this.destHand.add(card);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getPiecesLeft() {
		return piecesLeft;
	}

	public void setPiecesLeft(int piecesLeft) {
		this.piecesLeft = piecesLeft;
	}
}