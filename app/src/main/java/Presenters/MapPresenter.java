package Presenters;

import android.graphics.PointF;
import android.util.Log;

import java.lang.reflect.Type;
import java.util.EnumSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import Models.ActiveGame;
import Models.MainModel;
import Models.Player;
import Phase2Models.City;
import Phase2Models.DestinationCard;
import Phase2Models.MapModel;
import Phase2Models.Store;

import Phase2Services.ChatService;
import Phase2Services.ClaimRouteService;
import Phase2Services.DrawDestCardService;
import Services.Service;
import Views.Activities.MapActivity;
//import View.MapActivity;

public class MapPresenter implements Observer {
    static String TAG = "Presenter";
    MapActivity mapActivity;
    Map<Type, Set<Runnable>> typeToMethod;
    public MapPresenter(MapActivity mapActivity) {
        this.mapActivity = mapActivity;
        MainModel.get().addMapObservers(this);
        //showDestDialog();
    }

    @Override
    public void update(Observable o, Object arg) {
        String type = o.getClass().getName();

        switch (type){
            case "Player":
                mapActivity.updatePlayerInfo((Player)arg);
                break;
            case "Store":
                mapActivity.updateStore((Store)arg);
                break;
            case "ActiveGame":
                mapActivity.updateGameInfo((ActiveGame)arg);
                break;
            case "Phase2Models.MapModel":
                mapActivity.updateMap((MapModel) arg);
                break;
            case "ChatQueue":
                //mapActivity.updateChat((ChatQueue)o);
                break;
            default:
                Log.d(TAG, "Type " +type +" is not being checked");
        }
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
    public void selectCity(float x, float y,PointF size){
        //Loop over cities and check distance between this point and the city point
        //If distance is <= a specified radius, the city will be specified
        //If no city is currently selected selectCity();
        //If same city as currently selected, deselectCity()
        //If city is not directly connected, do nothing
        //If city is directly connected, claimRoute()
        for(City city : City.values()){
            PointF point = MapEquations.getPoint(city,size);
            float dist = (float)Math.pow(Math.pow(x-point.x,2) + Math.pow(y-point.y,2),.5);

            if(dist < 30){
                selectCity(city);
                return;
            }
        }
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
        MainModel.get().getMapModel().setSelectedCity(city);

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

    public void showDestDialog(){
        //EnumSet<DestinationCard> destHand = MainModel.get().getPlayer().getDestHand();
        //this.mapActivity.setDialogInfo(destHand);
    }

}
