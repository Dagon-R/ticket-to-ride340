package Models;

import java.util.TreeSet;

public interface IGame {
	Boolean addPlayer(String newPlayer);

	Boolean removePlayer(String targetPlayer);

	TreeSet<String> getPlayers();

	Boolean playerIsInGame(String player);

	String getGameName();
}