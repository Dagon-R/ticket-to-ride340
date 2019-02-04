


public class User {
	//The unique name of this user
	String name;
	//The password for this user
	String password;
	
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