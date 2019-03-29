package Models;

import java.util.EnumMap;
import java.util.Objects;
import java.util.TreeSet;

import Phase2Models.DestinationCard;
import Phase2Models.DestinationDeck;
import Phase2Models.Route;
import Phase2Models.Store;
import Phase2Models.TrainCardColor;
import Phase2Models.TrainDeck;

public class ActiveGame{


	//A list of the players associated with the game
	private TreeSet<String> players = new TreeSet<>();

	//The gameName of the game that will be displayed in menus
	private String gameName;
	//The unique gameId that represents this game
	private String gameId;

	private EnumMap<Route, Player[]> routeOwners;

	private Store store;

	private DestinationDeck destDeck;

	private TrainDeck trainDeck;

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

	public Store getStore() {
		return store;
	}

	public TrainDeck getTrainDeck() {
		return trainDeck;
	}

	public ActiveGame(){}
	
	public ActiveGame(String host, String gameName){
		players.add(host);
		this.gameName = gameName;
		routeOwners = new EnumMap<>(Route.class);
	}
	
	ActiveGame(PendingGame startGame){
		players.addAll(startGame.getPlayers());
		this.gameName = startGame.getGameName();
		this.gameId = startGame.getId() + "_ACTIVE";
		routeOwners = new EnumMap<>(Route.class);
	}

	public void discardDestCard(DestinationCard card)
	{
		destDeck.discard(card);
	}
	
	public Boolean addPlayer(String newPlayer){
		return players.add(newPlayer);
	}
	
	public Boolean removePlayer(String targetPlayer){
		return players.remove(targetPlayer);
	}
	
	public TreeSet<String> getPlayers(){
		return players;
	}
	
	public String getGameName(){
		return gameName;
	}

	public Boolean playerIsInGame(String player){
		return players.contains(player);
	}

	private void setPlayers(TreeSet<String> input){
		this.players = input;
	}
	
	private void setGameName(String input){
		this.gameName = input;
	}
	
	private void setGameId(String input){
		this.gameId = input;
	}

	public DestinationDeck getDestDeck() {
		return destDeck;
	}

	public void setDestDeck(DestinationDeck destDeck) {
		this.destDeck = destDeck;

	}

	public void setTrainDeck(TrainDeck trainDeck) {
		this.trainDeck = trainDeck;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ActiveGame)) return false;
		ActiveGame that = (ActiveGame) o;
		return Objects.equals(getGameName(), that.getGameName());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getGameName());
	}

	@Override
	public String toString() {
		return "ActiveGame{" +
				"players=" + players +
				", gameName='" + gameName + '\'' +
				", gameId='" + gameId + '\'' +
				'}';
	}
}