package Models;

import android.util.Log;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeSet;

import Phase2Models.ChatMessage;
import Phase2Models.ChatQueue;
import Phase2Models.City;
import Phase2Models.DestinationCard;
import Phase2Models.Route;
import Phase2Models.Store;
import Phase2Models.TrainCardColor;

public class ActiveGame extends Observable {
	private static String TAG = "ActiveGame";
	//A list of the players associated with the game
	private TreeSet<Player> players ;
	//The name of the game that will be displayed in menus
	private String name;
	//The unique id that represents this game
	private String id;
	private ThisPlayer player;
	//New Field
	private Store store;
	private ChatQueue queue;
    private EnumMap<Route, Player[]> routeOwners;
    private int destDeckSize;
    private int trainDeckSize;
    private int activePlayerInd;


	public ActiveGame(PendingGame startGame){
		players = new TreeSet<>();
		addPlayers(startGame.getPlayers());

//		players.addAll();

		this.name = startGame.getName();
		this.id = startGame.getId() + "_ACTIVE";
		routeOwners = new EnumMap<>(Route.class);
		queue= new ChatQueue();
		//Sorry for the hard coding but they're always the same so sue me -_o_-
		this.destDeckSize = 30;
		this.trainDeckSize = 110;
		this.activePlayerInd = 0;

	}

	private void addPlayers(ArrayList<String> players){
		int i =0;
		Log.d(TAG, "addPlayers: " + players);
		for(String name : players){
		    Player player;
			if(name.equals(MainModel.get().getUsername())){
			    player = new ThisPlayer(name,PlayerColorEnum.values()[i]);
				this.player = (ThisPlayer) player;
			}
			else
            {
                player = new OtherPlayer(name,PlayerColorEnum.values()[i]);
            }
			this.players.add(player);
			i++;
		}
	}

	public void setRouteOwner(City city1, City city2){
		Route route = Route.getRoute(city1,city2);
		if(route != null){
			routeOwners.put(route,new Player[]{player});
			return;
		}
		MainModel.get().setErrorMessage(city1.getName() + " is not directly next to "+ city2);
	}

	public Player[] getRouteOwners(Route route) {
		return routeOwners.get(route);
	}

	// Used for setting route owners for single routes
	public void setRouteOwner(Route route, Player player)
	{
		Player players[] = new Player[1];
		players[0] = player;
		routeOwners.put(route,players);
	}
	// Used for setting route owners for double routes
	public void setRouteOwner(Route route, Player player, boolean isSecond)
	{
		Player players[] = new Player[2];
		if (isSecond) { players[0] = routeOwners.get(route)[0]; players[1] = player; }
		else {players[0] = player; players[1] = routeOwners.get(route)[1];}
		routeOwners.put(route,players);
	}

    public void addChatMessage(ChatMessage message){
		queue.add(message);
	}
	public Boolean addPlayer(Player newPlayer){
		return players.add(newPlayer);
	}
	
	public Boolean removePlayer(Player targetPlayer){
		return players.remove(targetPlayer);
	}
	
	public TreeSet<Player> getPlayers(){
		return players;
	}
	public Player getPlayer(String name)
	{
		for (Player player : players)
		{
			if (player.getName().equals(name)) {return player;}
		}
		return null;
	}

	public String getName(){
		return name;
	}
	
	public String getId(){
		return id;
	}

	public void addObservers(Observer o){
		store.addObserver(o);
		queue.addObserver(o);
		player.addObserver(o);
		this.addObserver(o);
	}

	public void doStuff() {
        for (Player player : MainModel.get().getGame().getActiveGame().getPlayers()) {
            player.incrementScore(15);
            player.addToDestHand(DestinationCard.DUL_ELPASO);
            player.addTrainCard(TrainCardColor.BLACK);
            player.decrementPiecesLeft(15);
        }
        setChanged();
        notifyObservers(this);
    }

	public void addActionBarObservers(Observer o){
		store.addObserver(o);

/*
case "ErrorMessage":
                UtilPresenter.runOnUI(mapActivity,new Runnable() {
                    @Override
                    public void run() {
                        mapActivity.popToast(MainModel.get().getErrorMessage());
                    }
                });
                break;
            case "Store":
                updateStore();
                break;
            case "TrainCardColor":
                updateTrainDeck();
                break;

            case "DestinationCard":
                updateDestDeck();
                break;
            case "Turn":
                updateTurn();
 */
	}

	public void addChatObserver(Observer o){
		getChatQueue().addObserver(o);
	}

 	public void addPlayerInfoObserver(Observer o){
 		player.addObserver(o);
	}

    public EnumMap<Route, Player[]> getRouteOwners() {
        return routeOwners;
    }

    private void setPlayers(TreeSet<Player> input){
		this.players = input;
	}
	
	private void setName(String input){
		this.name = input;
	}
	
	private void setId(String input){
		this.id = input;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
		setChanged();
		notifyObservers(store);
	}

	public ThisPlayer getPlayer() {
		return player;
	}

	public void setPlayer(ThisPlayer player) {
		this.player = player;
	}

	public ChatQueue getChatQueue() {
		return queue;
	}

	public void setQueue(ChatQueue queue) {
		this.queue = queue;
	}

	public int getDestDeckSize() {
		return destDeckSize;
	}

	public void setDestDeckSize(int destDeckSize) {
		this.destDeckSize = destDeckSize;
		setChanged();
		notifyObservers(this);
//		updateDestDeckSize();
	}

	public void updateDestDeckSize(){
		//iterate through player's destDeck sizes and subtract from total
		int totalDeckSize = 30; //30 total destCardds
		for(Player player : this.getPlayers()){
			totalDeckSize -= player.getTotalDestinationCards();
		}
		this.destDeckSize = totalDeckSize;
		setChanged();
		notifyObservers(this);
	}

	public void updateTrainDeckSize(){
		//iterate through player's trainDeck sizes and subtract from total
		int totalDeckSize = 110; //110 total trainCards
		for(Player player : this.getPlayers()){
			totalDeckSize -= player.getTotalTrainCards();
		}
		this.trainDeckSize = totalDeckSize;
	}

	public void setTrainDeckSize(int deckSize) {
		this.trainDeckSize = deckSize;
	}

	public int getTrainDeckSize() {
		return this.trainDeckSize;
	}

	public void decrementTrainCards(int numDrawn){
		this.trainDeckSize -= numDrawn;
	}

	public int getActivePlayerInd() {
		return activePlayerInd;
	}

	public void incActivePlayerInd(){
		int numPlayers = this.players.size();
		this.activePlayerInd += 1;
		//wraparound to player 0
		if(this.activePlayerInd > numPlayers - 1) this.activePlayerInd = 0;
	}


}