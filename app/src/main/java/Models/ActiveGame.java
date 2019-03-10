package Models;

import android.util.Log;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeSet;

import Phase2Models.ChatMessage;
import Phase2Models.ChatQueue;
import Phase2Models.Route;
import Phase2Models.Store;

public class ActiveGame extends Observable {
	static String TAG = "ActiveGame";
	//A list of the players associated with the game
	private TreeSet<Player> players ;
	//The name of the game that will be displayed in menus
	private String name;
	//The unique id that represents this game
	private String id;
	private Player player;
	//New Field
	private Store store;
	private ChatQueue queue;
    private EnumMap<Route,Player> routeOwners;
//	public ActiveGame(){}

	//Should be called from ClientGameList.startGame
//	public ActiveGame(Player host, String gameName){
//		players = new TreeSet<>();
//
//		players.add(host);
//		this.name = gameName;
//		this.id = gameName + host.getName() + "_ACTIVE";
//        queue= new ChatQueue();
//        routeOwners = new EnumMap<>(Route.class);
//	}
	
	public ActiveGame(PendingGame startGame){
		players = new TreeSet<>();
		addPlayers(startGame.getPlayers());

//		players.addAll();

		this.name = startGame.getName();
		this.id = startGame.getId() + "_ACTIVE";
		routeOwners = new EnumMap<>(Route.class);
		queue= new ChatQueue();

//		store = new Store();
	}

	private void addPlayers(ArrayList<String> players){
		int i =0;
		Log.d(TAG, "addPlayers: " + players);
		for(String name : players){
			Player player = new Player(name,PlayerColorEnum.values()[i]);

			if(name.equals(MainModel.get().getUsername())){
				this.player = player;

			}
			this.players.add(player);
			i++;
		}
	}
    public Player getOwner(Route route) {return routeOwners.get(route);}

    public void addChatMessage(ChatMessage message)
	{
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
	
	public String getName(){
		return name;
	}
	
	public String getId(){
		return id;
	}

	public void addObservers(Observer o){
//		store.addObserver(o);
		queue.addObserver(o);
		this.addObserver(o);
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
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ChatQueue getChatQueue() {
		return queue;
	}

	public void setQueue(ChatQueue queue) {
		this.queue = queue;
	}
}