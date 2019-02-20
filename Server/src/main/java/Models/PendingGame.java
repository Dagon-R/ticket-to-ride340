package Models;

import java.util.TreeSet;
import java.util.Objects;

public class PendingGame implements IGame{
	//A list of the players associated with the game
	private TreeSet<Player> players = new TreeSet<>();
	//The name of the game that will be displayed in menus
	private String name;
	//The unique id that represents this game
	private String id;
	
	public PendingGame(){}
	
	public PendingGame(Player host, String gameName){

		players.add(host);
		this.name = gameName;
		this.id = gameName + host.getName();
	}
	
	public Boolean addPlayer(Player newPlayer){
		return players.add(newPlayer);
	}
	
	public Boolean removePlayer(Player targetPlayer){
		return players.remove(targetPlayer);
	}
	
	public TreeSet<Player> getPlayers(){
		return players;
	}

	public Boolean playerIsInGame(Player player){
        return players.contains(player);
    }
	
	public String getGameName(){
		return name;
	}
	
	public String getId(){
		return id;
	}
	
	private void setPlayers(TreeSet<Player> input){
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