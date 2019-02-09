package Models;

import java.util.HashSet;

public interface IGame {
	Boolean addPlayer(Player newPlayer);

	Boolean removePlayer(Player targetPlayer);

	HashSet<Player> getPlayers();

	String getName();
}