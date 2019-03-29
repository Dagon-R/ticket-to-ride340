package Models;

import java.util.ArrayList;
import java.util.Objects;

public class PendingGame{
	//A list of the players associated with the game
	private ArrayList<String> players;
	//The name of the game that will be displayed in menus
	private String name;
	//The unique id that represents this game
	private String id;
	//player object for this client
	private String player;

	public PendingGame(String host, String gameName){
		players = new ArrayList<>();
		players.add(host);
		this.name = gameName;
		this.id = gameName + host;
	}

	public Boolean addPlayer(String newPlayer){
		return players.add(newPlayer);
	}
	
	public Boolean removePlayer(String targetPlayer){
		return players.remove(targetPlayer);
	}

	public ArrayList<String> getPlayers(){
		return players;
	}
	
	public String getName(){
		return name;
	}
	
	public String getId(){
		return id;
	}
	
	private void setPlayers(ArrayList<String> input){
		this.players = input;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}
	
	private void setName(String input){
		this.name = input;
	}
	
	private void setId(String input){
		this.id = input;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PendingGame)) return false;
		PendingGame that = (PendingGame) o;
		return Objects.equals(getName(), that.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName());
	}
}