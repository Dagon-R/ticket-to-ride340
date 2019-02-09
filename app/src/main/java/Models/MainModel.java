package Models;

import java.util.Observable;

public class MainModel extends Observable {
    private static MainModel instance;
    private Player player;
    private User user;
    private IGame game;
    private ClientGameList gameList;

    private MainModel() {}

    public static MainModel get(){
        if(instance == null){
            instance = new MainModel();
        }
        return instance;
    }

    public static MainModel getInstance() {
        return instance;
    }

    public static void setInstance(MainModel instance) {
        MainModel.instance = instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IGame getGame() {
        return game;
    }

    public void setGame(IGame game) {
        this.game = game;
        this.notifyObservers();
    }

    public ClientGameList getGameList() {
        return gameList;
    }

    public void setGameList(ClientGameList gameList) {
        this.gameList = gameList;
    }
}
