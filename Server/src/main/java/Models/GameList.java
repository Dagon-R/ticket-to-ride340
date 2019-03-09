package Models;

import java.util.HashMap;

import Phase2Models.DestinationDeck;
import Phase2Models.InvalidStoreLengthException;
import Phase2Models.Store;
import Phase2Models.TrainDeck;

public class GameList {
    //A list of games that are currently waiting to start
    private HashMap<String,ActiveGame> ServerActiveGames;
    //A list of games that are currently running
    private HashMap<String,PendingGame> ServerPendingGames;

    GameList() {
        ServerActiveGames = new HashMap<>();
        ServerPendingGames = new HashMap<>();
    }

    public IGame get(String name){
        IGame game = ServerActiveGames.get(name);
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
        ServerPendingGames.put(newGame.getGameName(),newGame);
    }

    public void addServerActiveGame(ActiveGame newGame){
        ServerActiveGames.put(newGame.getGameName(),newGame);
    }

    public void removeServerPendingGame(PendingGame targetGame){
        if(ServerPendingGames.containsKey(targetGame.getGameName())){
            ServerPendingGames.remove(targetGame.getGameName());
        }

    }

    public void removeServerActiveGame(ActiveGame targetGame){
        if(ServerActiveGames.containsKey(targetGame.getGameName())){
            ServerActiveGames.remove(targetGame.getGameName());
        }

    }

    public ActiveGame startGame(String name){
        if(ServerPendingGames.containsKey(name)){
            PendingGame game = ServerPendingGames.get(name);
            ServerPendingGames.remove(name);
            ActiveGame ag = new ActiveGame(game);
            addServerActiveGame(ag);
            ag.setDestDeck(new DestinationDeck());
            ag.setTrainDeck(new TrainDeck());
            return ag;
        }
        return null;

    }

    @Override
    public String toString() {
        return "GameList{" +
                "ServerActiveGames=" + ServerActiveGames +
                ", ServerPendingGames=" + ServerPendingGames +
                '}';
    }
}