package Models;

import java.util.TreeSet;

public interface IGame {
	Boolean addPlayer(Player newPlayer);

	Boolean removePlayer(Player targetPlayer);

	TreeSet<Player> getPlayers();

	Boolean playerIsInGame(Player player);

	String getGameName();
}