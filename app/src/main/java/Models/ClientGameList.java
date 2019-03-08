package Models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;

public class ClientGameList extends Observable {
	//A list of games that are currently waiting to start

	//A list of games that are currently running
	HashMap<String,PendingGame> ServerPendingGames;

	public ClientGameList() {
		ServerPendingGames = new HashMap<>();
	}

	public PendingGame get(String name){
		PendingGame game = ServerPendingGames.get(name);

		return game ;
	}

	public void setServerPendingGames(HashMap<String,PendingGame> pendingGames){
		ServerPendingGames = pendingGames;
		setChanged();
		notifyObservers();
	}


	public HashMap<String,PendingGame> getServerPendingGames(){
		return ServerPendingGames;
	}

	public void addServerPendingGame(PendingGame newGame){
		ServerPendingGames.put(newGame.getName(),newGame);
	}



	public void removeServerPendingGame(PendingGame targetGame){
		if(ServerPendingGames.containsKey(targetGame.getName())){
			ServerPendingGames.remove(targetGame.getName());
		}

	}



	public ActiveGame startGame(String name){

		if(ServerPendingGames.containsKey(name)){
			PendingGame game = ServerPendingGames.get(name);
			ServerPendingGames.remove(name);
			ActiveGame ag = new ActiveGame(game);
			return ag;
		}
		return null;

	}
}