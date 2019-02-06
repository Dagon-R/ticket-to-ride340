package Models;


public class Player {
	//The color that represents this player
	PlayerColorEnum playerColor;
	//The name of this player
	String name;
	//The unique id of this player
	String id;
	
	public PlayerColorEnum getColor(){
		return playerColor;
	}
	
	public String getName(){
		return name;
	}
	
	public String getId(){
		return id;
	}
	
	private void setColor(PlayerColorEnum input){
		this.playerColor = input;
	}
	
	private void setName(String input){
		this.name = input;
	}
	
	private void setId(String input){
		this.id = input;
	}
	
}