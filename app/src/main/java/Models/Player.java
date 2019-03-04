package Models;


import java.util.ArrayList;

import Phase2Models.DestinationCard;
import Phase2Models.TrainCardColor;

public class Player {
	//The color that represents this player
	private PlayerColorEnum playerColor;
	//The name of this player
	private String name;


	// New Fields
	private ArrayList<TrainCardColor> trainHand;
	private ArrayList<DestinationCard> destHand;
	private int score;
	private int piecesLeft;


	public Player(String name, PlayerColorEnum playerColor){
		this.name = name;
		this.playerColor = playerColor;
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

	public ArrayList<DestinationCard> getDestHand() {
		return destHand;
	}

	public void setDestHand(ArrayList<DestinationCard> destHand) {
		this.destHand = destHand;
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