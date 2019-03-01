package Presenters;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import Models.ActiveGame;
import Models.Player;
import Phase2Models.City;
import Phase2Models.Store;
import Phase2Services.ChatService;
import Phase2Services.ClaimRouteService;
import Phase2Services.DrawDestCardService;
import Services.Service;
import Views.MapActivity;

public class MapPresenter implements Observer {
    MapActivity mapActivity;
    Map<Type, Set<Runnable>> typeToMethod;
    public MapPresenter(MapActivity mapActivity) {
        this.mapActivity = mapActivity;

    }



    @Override
    public void update(Observable observable, Object o) {
        String type = observable.getClass().getName();
        switch (type){
            case "Player":
                mapActivity.updatePlayerInfo((Player)o);
                break;
            case "Store":
                mapActivity.updateStore((Store)o);
                break;
            case "ActiveGame":
                mapActivity.updateGameInfo((ActiveGame) o);
                break;
            case "Map":
//                mapActivity.updateMap((Map)o);
                break;
            case "ChatQueue":
                //mapActivity.updateChat((ChatQueue)o);
                break;
        }
        //
    }
    public void drawDestination(){
        Service drawDestinationService = new DrawDestCardService();
//        drawDestinationService.
    }
    public void drawStore(int index){
        Service drawStoreService = null;

    }
    public void drawTrainCard() {
        Service drawTrainCardService = null;
    }
    public void selectCity(float x, float y){
        //Loop over cities and check distance between this point and the city point
        //If distance is <= a specified radius, the city will be specified
        //If no city is currently selected selectCity();
        //If same city as currently selected, deselectCity()
        //If city is not directly connected, do nothing
        //If city is directly connected, claimRoute()
    }
    private void claimRoute(){
        //Send prompt to view with message "Claim route between %city1 and %city2?" (place names in for city1 and city2)
        //Prompt should also include "Requires %numberRequired %color train car cards"
        //Prompt includes "You have %numberOwned %color train car cards"
        //Prompt may also include "You must use %numberRequired - numberOwned rainbow car cards"
        //Options for confirm and deny
        //(Next parts will not be called from here, but will include the sequence of events)
        //If confirmed, call confirmRoute()
        //If deny, deselectCity()
    }

    public void confirmRoute(){
        //Confirm route after being prompted
        Service claimRouteService = new ClaimRouteService();
    }

    private void selectCity(City city){
        //update model. Selected city1 = city
    }

    private void deselectCity(){
        //update model. Selected
    }
    public void sendMessage(String message){
        //Create ChatMessage object, retrieve UTC time stamp, and send to service
        Service chatService = new ChatService();

    }
    private void advanceTurn(){

    }
}
