package Models;

import java.util.Observable;
import java.util.Observer;

import Phase2Models.Activities;
import Phase2Models.ActivityTypes;
import Phase2Models.ChatQueue;
import Phase2Models.MapModel;
import Phase2Models.Store;


import Phase2Models.DestinationCard;

public class MainModel{
    private static String TAG = "MainModel";
    private static MainModel instance;
    private String username;
    private User user;
    private Game game;
    private ClientGameList gameList;
//    private String errorMessage;
    private String IPAddress;
    private ErrorMessage errorMessage;
    private MapModel mapModel;
    private Activities activity;


    private MainModel() {
        game = new Game();
        gameList = new ClientGameList();
        errorMessage = new ErrorMessage();
        user = new User();
        activity = new Activities(ActivityTypes.LobbyActivity);
    }

    public static MainModel get(){
        if(instance == null){
            instance = new MainModel();
        }
        return instance;
    }

    public void discardCard(DestinationCard card) {
        game.getActiveGame().getPlayer().removeDestCard(card);
    }

    public MapModel getMapModel(){
        return mapModel;
    }

    public void addLoginObservers(Observer o){
        errorMessage.addObserver(o);
        gameList.addObserver(o);
    }

    public void removeLoginObservers(Observer o){
        errorMessage.deleteObserver(o);
        gameList.deleteObserver(o);
    }

    public void addChooseGameObservers(Observer o){
        errorMessage.addObserver(o);
        gameList.addObserver(o);
        game.addObserver(o);
    }

    public void removeChooseGameObservers(Observer o){
        errorMessage.deleteObserver(o);
        gameList.deleteObserver(o);
        game.deleteObserver(o);
    }

    public void addLobbyObservers(Observer o){
        gameList.addObserver(o);
        game.addObserver(o);
    }

    public void removeLobbyObservers(Observer o){
        gameList.deleteObserver(o);
        game.deleteObserver(o);
    }

    public void addMapObservers(Observer o){
//        mapModel.addObserver(o);
        game.addObserver(o);
        game.getActiveGame().addObservers(o);
    }

    public void activateGame(String gameName, Store store){
        gameList.startGame(gameName);
        ActiveGame game = new ActiveGame(this.game.getPendingGame());
        setActiveGame(game);
        game.setStore(store);
//        ((ActiveGame) game).setStore(store);
        createMapActivity();
    }

    private void createMapActivity(){
        mapModel = new MapModel();
        activity.setCurrentActivty(ActivityTypes.MapActivity);

    }

    public Player getPlayer() {
        return game.getActiveGame().getPlayer();
    }

    public void setPlayer(Player player) {
        game.getActiveGame().setPlayer(player);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setPendingGame(PendingGame game){
        this.game.setPendingGame(game);
    }

    public void setActiveGame(ActiveGame game) {
        this.game.setActiveGame(game);
    }

    public ClientGameList getGameList() {
        return gameList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        this.gameList.setServerPendingGames(gameList.getServerPendingGames());

    }

    public void addPlayerToGame(String gameName, String player){
        gameList.addPlayerToGame(gameName,player);

    }

    public String getErrorMessage() {
        return errorMessage.getError();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage.setError(errorMessage);
    }


    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public String getAuthToken() {
        return user.getAuthToken();
    }

    public void setAuthToken(String authToken) {
        user.setAuthToken(authToken);

    }

    public void addGameToGameList(PendingGame newGame) {
        this.getGameList().addServerPendingGame(newGame);
    }
    public Store getStore() {return this.game.getActiveGame().getStore();}
    public ChatQueue getChatQueue() {return this.game.getActiveGame().getChatQueue();}
}
