package Models

public interface IGame {
	public Boolean addPlayer(Player newPlayer);
	
	public Boolean removePlayer(Player targetPlayer);
	
	public HashSet<Player> getPlayers();
	
	public String getName();
	
	public String getId();
}