package Models;

import java.util.HashSet;
import java.util.Objects;

public class ActiveGame implements IGame{
	//A list of the players associated with the game
	private HashSet<Player> players = new HashSet<>();
	//The name of the game that will be displayed in menus
	private String name;
	//The unique id that represents this game
	private String id;
	
	public ActiveGame(){}
	
	public ActiveGame(Player host, String gameName){
		players.add(host);
		this.name = gameName;
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
	

	
	private void setPlayers(HashSet<Player> input){
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
		if (!(o instanceof IGame)) return false;
		IGame that = (IGame) o;
		return Objects.equals(getName(), that.getName());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getName());
	}
}