package Models;

import java.util.HashSet;

public class GameList {
    //A list of games that are currently waiting to start
    HashSet<ActiveGame> ServerActiveGames = new HashSet<>();
    //A list of games that are currently running
    HashSet<PendingGame> ServerPendingGames = new HashSet<>();

    public HashSet<ActiveGame> getServerActiveGames(){
        return ServerActiveGames;
    }

    public HashSet<PendingGame> getServerPendingGames(){
        return ServerPendingGames;
    }

    public void addServerPendingGame(PendingGame newGame){
        ServerPendingGames.add(newGame);
    }

    public void addServerActiveGame(ActiveGame newGame){
        ServerActiveGames.add(newGame);
    }

    public void removeServerPendingGame(PendingGame targetGame){
//        for(PendingGame game : ServerPendingGames){
//            if(game.getId().equals(gameId)){
//                ServerPendingGames.remove(game);
//            }
//        }
    }

    public void removeServerActiveGame(ActiveGame targetGame){
//        for(ActiveGame game : ServerActiveGames){
//            if(game.getId().equals(gameId)){
//                ServerActiveGames.remove(game);
//            }
//        }
    }

    public void startGame(String gameId){
        for(PendingGame game : ServerPendingGames){
            if(game.getId().equals(gameId)){
                addServerActiveGame(new ActiveGame(game));
                ServerPendingGames.remove(game);
            }
        }
    }
}