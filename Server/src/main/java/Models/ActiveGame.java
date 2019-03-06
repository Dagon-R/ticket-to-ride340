package Models;

import java.util.EnumMap;
import java.util.Objects;
import java.util.TreeSet;

import Phase2Models.DestinationCard;
import Phase2Models.DestinationDeck;
import Phase2Models.Route;
import Phase2Models.Store;
import Phase2Models.TrainDeck;

public class ActiveGame implements IGame{
	//A list of the players associated with the game
	private TreeSet<Player> players = new TreeSet<>();

	
	//The gameName of the game that will be displayed in menus
	private String gameName;
	//The unique gameId that represents this game
	private String gameId;

	private EnumMap<Route,Player> routeOwners;

	private Store store;

	private DestinationDeck destDeck;

	private TrainDeck trainDeck;

	public Player getRouteOwner(Route route) {
		return routeOwners.get(route);
	}

	public Store getStore() {
		return store;
	}

	public TrainDeck getTrainDeck() {
		return trainDeck;
	}

	public ActiveGame(){}
	
	public ActiveGame(Player host, String gameName){
		players.add(host);
		this.gameName = gameName;
	}
	
	ActiveGame(PendingGame startGame){
		players.addAll(startGame.getPlayers());
		this.gameName = startGame.getGameName();
		this.gameId = startGame.getId() + "_ACTIVE";
		this.destDeck = new DestinationDeck();
	}

	public void discardDestCard(DestinationCard card)
	{
		destDeck.discard(card);
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
	
	public String getGameName(){
		return gameName;
	}

	public Boolean playerIsInGame(Player player){
		return players.contains(player);
	}

	private void setPlayers(TreeSet<Player> input){
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof IGame)) return false;
		IGame that = (IGame) o;
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