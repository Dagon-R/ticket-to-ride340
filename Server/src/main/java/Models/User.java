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
	
	public void setLoggedIn(Boolean state){
		this.loggedIn = state;
	}
	
	public void setAuthToken(String token){
		this.authToken = token;
	}
}