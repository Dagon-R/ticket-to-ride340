package Models;

public class User {
	//The unique name of this user
	String name;
	//The password for this user
	String password;
	//Says whether the current instance of the Client is logged in
	Boolean loggedIn = false;
	//The auth token for server verification (if necessary)
	String authToken;
	// The player color of the current player
	PlayerColorEnum playerColor;

	public String getId() { return id; }

	// The player's identification number
	String id;
	
	public PlayerColorEnum getColor(){
		return playerColor;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPassword(){
		return password;
	}
	
	public Boolean getLoggedIn(){
		return loggedIn;
	}
	
	public String getAuthToken(){
		return authToken;
	}
	
	public void setColor(PlayerColorEnum input){
		this.playerColor = input;
	}
	
	public void setName(String input){
		this.name = input;
	}
	
	public void setPassword(String input){
		this.id = password;
	}
	
	public void setLoggedIn(Boolean state){
		this.loggedIn = state;
	}
	
	public void setAuthToken(String token){
		this.authToken = token;
	}
}