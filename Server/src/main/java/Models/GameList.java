package Models;

import java.util.HashSet;

public class GameList {
    //A list of games that are currently waiting to start
    HashSet<ActiveGame> ServerActiveGames;
    //A list of games that are currently running
    HashSet<PendingGame> ServerPendingGames;

    public GameList(HashSet<ActiveGame> serverActiveGames, HashSet<PendingGame> serverPendingGames) {
        ServerActiveGames = new HashSet<>();
        ServerPendingGames = new HashSet<>();
    }

    public boolean has(String name){
        IGame game = new ActiveGame(null, name);

        return ServerActiveGames.contains(game) || ServerPendingGames.contains(game);
    }

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
        for(PendingGame game : ServerPendingGames){
            if(game.getId().equals(targetGame.getId())){
                ServerPendingGames.remove(game);
            }
        }
    }

    public void removeServerActiveGame(ActiveGame targetGame){
        for(ActiveGame game : ServerActiveGames){
            if(game.getName().equals(targetGame.getName())){
                ServerActiveGames.remove(game);
            }
        }
    }

    public void startGame(String name){
        for(PendingGame game : ServerPendingGames){
            if(game.getName().equals(name)){
                addServerActiveGame(new ActiveGame(game));
                ServerPendingGames.remove(game);
            }
        }
    }
}