package Models;

import java.util.HashMap;
import java.util.Observable;

public class ClientGameList extends Observable {
	//A list of games that are currently waiting to start

	//A list of games that are currently running
	private HashMap<String,PendingGame> ServerPendingGames;

	public ClientGameList() {
		ServerPendingGames = new HashMap<>();
	}

	public PendingGame get(String name){

		return ServerPendingGames.get(name);
	}

	public void setServerPendingGames(HashMap<String,PendingGame> pendingGames){
		ServerPendingGames = pendingGames;
		setChanged();
		notifyObservers();
	}

	public void addPlayerToGame(String game, String player){
		ServerPendingGames.get(game).addPlayer(player);
		setChanged();
		notifyObservers(this);
	}


	public HashMap<String,PendingGame> getServerPendingGames(){
		return ServerPendingGames;
	}

	public void addServerPendingGame(PendingGame newGame){
		ServerPendingGames.put(newGame.getName(),newGame);
		setChanged();
		notifyObservers(this);
	}



	public void removeServerPendingGame(PendingGame targetGame){
		if(ServerPendingGames.containsKey(targetGame.getName())){
			ServerPendingGames.remove(targetGame.getName());
		}

	}



	public void unPendGame(String name){
		if(ServerPendingGames.containsKey(name)){
			ServerPendingGames.remove(name);
		}
	}
}