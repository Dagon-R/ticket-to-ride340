package Models;

import java.util.HashSet;

public interface IGame {
	Boolean addPlayer(Player newPlayer);

	Boolean removePlayer(Player targetPlayer);

	HashSet<Player> getPlayers();

	Boolean playerIsInGame(Player player);

	String getName();
}