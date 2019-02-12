package Models;

import java.util.Observable;

public class MainModel extends Observable {
    private static MainModel instance;
    private Player player;
    private User user;
    private IGame game;
    private ClientGameList gameList;
    private String errorMessage;
    private String IPAddress;
    private String authToken;

    private MainModel() {
        user = new User();
    }

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
        setChanged();
        this.notifyObservers();
    }

    public IGame getGame() {
        return game;
    }

    public void setGame(IGame game) {
        this.game = game;
        setChanged();
        this.notifyObservers();
    }

    public ClientGameList getGameList() {
        return gameList;
    }

    public PendingGame findGame(String gameID){
        for(PendingGame game : gameList.getServerPendingGames().values()){
            if(game.getName().equals(gameID)){
                return game;
            }
        }
        return null;
    }

    public void setGameList(ClientGameList gameList){

        this.gameList = gameList;
        setChanged();
        notifyObservers();
    }

    public void addPlayerToGame(String gameName, Player player){
        gameList.get(gameName).addPlayer(player);
        setChanged();
        notifyObservers();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        setChanged();
        this.notifyObservers();
    }


    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
