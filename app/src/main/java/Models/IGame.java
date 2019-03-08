package Models;

import java.util.HashSet;
import java.util.TreeSet;

public interface IGame {
	Boolean addPlayer(String newPlayer);

	Boolean removePlayer(Player targetPlayer);

	TreeSet<String> getPlayers();

	String getName();

	String getId();
}