package Models;

public class User {
	//The unique name of this user
	private String name;
	//The password for this user
	private String password;
	private PlayerColorEnum playerColor;
	private String id;

	public User(String name, String password, PlayerColorEnum playerColor, String id){
		this.name = name;
		this.password = password;
		this.playerColor = playerColor;
		this.id = id;
	}
	
	public PlayerColorEnum getColor(){
		return playerColor;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPassword(){
		return password;
	}
	
	private void setColor(PlayerColorEnum input){
		this.playerColor = input;
	}
	
	private void setName(String input){
		this.name = input;
	}
	
	private void setPassword(String input){
		this.id = password;
	}
	
}