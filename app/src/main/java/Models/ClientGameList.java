package Models;

import java.util.HashMap;
import java.util.HashSet;

public class ClientGameList {
	//A list of games that are currently waiting to start
	HashMap<String,ActiveGame> ServerActiveGames;
	//A list of games that are currently running
	HashMap<String,PendingGame> ServerPendingGames;

	public ClientGameList() {
		ServerActiveGames = new HashMap<>();
		ServerPendingGames = new HashMap<>();
	}

	public IGame get(String name){
		IGame game =ServerActiveGames.get(name);
		if(game == null){
			game = ServerPendingGames.get(name);
		}
		return game ;
	}

	public HashMap<String,ActiveGame> getServerActiveGames(){
		return ServerActiveGames;
	}

	public HashMap<String,PendingGame> getServerPendingGames(){
		return ServerPendingGames;
	}

	public void addServerPendingGame(PendingGame newGame){
		ServerPendingGames.put(newGame.getName(),newGame);
	}

	public void addServerActiveGame(ActiveGame newGame){
		ServerActiveGames.put(newGame.getName(),newGame);
	}

	public void removeServerPendingGame(PendingGame targetGame){
		if(ServerPendingGames.containsKey(targetGame.getName())){
			ServerPendingGames.remove(targetGame.getName());
		}

	}

	public void removeServerActiveGame(ActiveGame targetGame){
		if(ServerActiveGames.containsKey(targetGame.getName())){
			ServerActiveGames.remove(targetGame.getName());
		}

	}

	public void startGame(String name){
		if(ServerPendingGames.containsKey(name)){
			PendingGame game = ServerPendingGames.get(name);
			ServerPendingGames.remove(name);
			addServerActiveGame(new ActiveGame(game));
		}

	}
}