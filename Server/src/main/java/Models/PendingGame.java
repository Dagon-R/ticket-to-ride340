package Models;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Objects;

public class PendingGame{
	//A list of the players associated with the game
	private ArrayList<String> players;
	//The name of the game that will be displayed in menus
	private String name;
	//The unique id that represents this game
	private String id;
	
	public PendingGame(){}

	
	
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

	public Boolean playerIsInGame(String player){
        return players.contains(player);
    }
	
	public String getGameName(){
		return name;
	}
	
	public String getId(){
		return id;
	}
	
	private void setPlayers(ArrayList<String> input){
		this.players = input;
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
		return Objects.equals(getGameName(), that.getGameName());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getGameName());
	}

	@Override
	public String toString() {
		return "PendingGame{" +
				"players=" + players +
				", name='" + name + '\'' +
				", id='" + id + '\'' +
				'}';
	}
}