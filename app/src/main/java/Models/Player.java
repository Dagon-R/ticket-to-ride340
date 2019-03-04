package Models;


import java.util.ArrayList;
import java.util.EnumSet;

import Phase2Models.DestinationCard;
import Phase2Models.TrainCardColor;

public class Player {
	//The color that represents this player
	private PlayerColorEnum playerColor;
	//The name of this player
	private String name;


	// New Fields
	private ArrayList<TrainCardColor> trainHand;
	private EnumSet<DestinationCard> destHand;
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
}