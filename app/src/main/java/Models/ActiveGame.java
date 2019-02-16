package Models;

import java.util.HashSet;

import Phase2Models.Store;

public class ActiveGame implements IGame{
	//A list of the players associated with the game
	private HashSet<Player> players = new HashSet<>();
	//The name of the game that will be displayed in menus
	private String name;
	//The unique id that represents this game
	private String id;

	//New Field
	private Store store;
	
	public ActiveGame(){}
	
	public ActiveGame(Player host, String gameName){
		players.add(host);
		this.name = gameName;
		this.id = gameName + host.getName() + "_ACTIVE";
	}
	
	public ActiveGame(PendingGame startGame){
		players.addAll(startGame.getPlayers());
		this.name = startGame.getName();
		this.id = startGame.getId() + "_ACTIVE";
	}
	
	public Boolean addPlayer(Player newPlayer){
		return players.add(newPlayer);
	}
	
	public Boolean removePlayer(Player targetPlayer){
		return players.remove(targetPlayer);
	}
	
	public HashSet<Player> getPlayers(){
		return players;
	}
	
	public String getName(){
		return name;
	}
	
	public String getId(){
		return id;
	}
	
	private void setPlayers(HashSet<Player> input){
		this.players = input;
	}
	
	private void setName(String input){
		this.name = input;
	}
	
	private void setId(String input){
		this.id = input;
	}
}