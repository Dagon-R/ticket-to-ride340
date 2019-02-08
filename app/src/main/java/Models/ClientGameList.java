package Models

public class ClientGameList {
	//A list of games that are currently waiting to start
	HashSet<ActiveGame> activeGames = new HashSet<>();
	//A list of games that are currently running
	HashSet<PendingGame> pendingGames = new HashSet<>();
	
	public HashSet<ActiveGame> getActiveGames(){
		return activeGames;
	}
	
	public HashSet<ActiveGame> getPendingGames(){
		return pendingGames;
	}
	
	public void addPendingGame(PendingGame newGame){
		pendingGames.add(newGame);
	}
	
	public void addActiveGame(ActiveGame newGame){
		activeGames.add(newGame);
	}

	public void removePendingGame(PendingGame targetGame){
		for(PendingGame game : pendingGames){
			if(game.getId().equals(gameId)){
				pendingGames.remove(game);
			}
		}
	}
	
	public void removeActiveGame(ActiveGame targetGame){
		for(ActiveGame game : activeGames){
			if(game.getId().equals(gameId)){
				activeGames.remove(game);
			}
		}
	}
	
	public void startGame(String gameId){
		for(PendingGame game : pendingGames){
			if(game.getId().equals(gameId)){
				addActiveGame(new ActiveGame(game));
				pendingGames.remove(game);
			}
		}
	}
}