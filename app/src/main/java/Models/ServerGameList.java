package Models

public class ServerGameList {
	//A list of games that are currently waiting to start
	HashSet<ServerActiveGame> ServerActiveGames = new HashSet<>();
	//A list of games that are currently running
	HashSet<ServerPendingGame> ServerPendingGames = new HashSet<>();
	
	public HashSet<ServerActiveGame> getServerActiveGames(){
		return ServerActiveGames;
	}
	
	public HashSet<ServerActiveGame> getServerPendingGames(){
		return ServerPendingGames;
	}
	
	public void addServerPendingGame(ServerPendingGame newGame){
		ServerPendingGames.add(newGame);
	}
	
	public void addServerActiveGame(ServerActiveGame newGame){
		ServerActiveGames.add(newGame);
	}

	public void removeServerPendingGame(ServerPendingGame targetGame){
		for(ServerPendingGame game : ServerPendingGames){
			if(game.getId().equals(gameId)){
				ServerPendingGames.remove(game);
			}
		}
	}
	
	public void removeServerActiveGame(ServerActiveGame targetGame){
		for(ServerActiveGame game : ServerActiveGames){
			if(game.getId().equals(gameId)){
				ServerActiveGames.remove(game);
			}
		}
	}
	
	public void startGame(String gameId){
		for(ServerPendingGame game : ServerPendingGames){
			if(game.getId().equals(gameId)){
				addServerActiveGame(new ServerActiveGame(game));
				ServerPendingGames.remove(game);
			}
		}
	}
}