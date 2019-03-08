package Models;

import java.util.HashSet;
import java.util.TreeSet;

public class PendingGame{
	//A list of the players associated with the game
	private TreeSet<String> players;
	//The name of the game that will be displayed in menus
	private String name;
	//The unique id that represents this game
	private String id;
	//player object for this client
	private Player player;
	
	public PendingGame(){}
	
	public PendingGame(String host, String gameName){
		players = new TreeSet<>();
		players.add(host);
		this.name = gameName;
		this.id = gameName + host;
	}

	public Boolean addPlayer(String newPlayer){
		return players.add(newPlayer);
	}
	
	public Boolean removePlayer(Player targetPlayer){
		return players.remove(targetPlayer);
	}

	public TreeSet<String> getPlayers(){
		return players;
	}
	
	public String getName(){
		return name;
	}
	
	public String getId(){
		return id;
	}
	
	private void setPlayers(TreeSet<String> input){
		this.players = input;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	private void setName(String input){
		this.name = input;
	}
	
	private void setId(String input){
		this.id = input;
	}
}