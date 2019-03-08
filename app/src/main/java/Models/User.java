package Models;

import java.util.Observable;

public class User extends Observable  { //implements JsonSerializer<User>
	//The unique name of this user
	private String name;
	//The password for this user
	private String password;
	//Says whether the current instance of the Client is logged in
	private boolean loggedIn = false;
	//The auth token for server verification (if necessary)
	private String authToken;


	public String getId() { return id; }

	// The player's identification number
	private String id;

	public User(UnobservedUser user)
	{
		this.name = user.getName();
		this.password = user.getPassword();
		this.loggedIn = user.getLoggedIn();
		this.authToken = user.getAuthToken();
	}

	public User(){
		this.name = "";
		this.password = "";
		this.loggedIn = false;
		this.authToken = "";
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
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

	public void setName(String input){
		this.name = input;
	}
	
	public void setPassword(String input){
		this.id = password;
	}
	
	public void setLoggedIn(Boolean state){
		this.loggedIn = state;
		setChanged();
		notifyObservers();
	}

	public void setAuthToken(String token){
		this.authToken = token;
	}
}