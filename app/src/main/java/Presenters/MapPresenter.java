package Presenters;

import android.graphics.PointF;
import android.util.Log;

import java.lang.reflect.Type;
import java.util.ArrayList;
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

import Phase2Models.Route;
import Phase2Models.Store;
import Phase2Models.TrainCardColor;
import Phase2Services.ChatService;
import Phase2Services.ClaimRouteService;
import Phase2Services.DrawDestCardService;
import Services.Service;
import views.activities.MapActivity;
//import View.MapActivity;

public class MapPresenter implements Observer {
    static String TAG = "Presenter";
    MapActivity mapActivity;
    Map<Type, Set<Runnable>> typeToMethod;
    ArrayList<DestinationCard> selectedDestCards;
    public MapPresenter(MapActivity mapActivity) {
        this.mapActivity = mapActivity;
        MainModel.get().addMapObservers(this);
        selectedDestCards = new ArrayList<>();
        showDestDialog();
        initActionBar();
        initialize();
    }

    private void initialize(){
        updatePlayer();
        updateStore();
//
        updateChat();
        updateMap();
        initActionBar();
        updateActiveGame();
    }


    @Override
    public void update(Observable o, Object arg) {
        String type = o.getClass().getSimpleName();
        if(arg != null){
            type = arg.getClass().getSimpleName();
        }
        System.out.println(arg);
        if(type.equals("String")){
            type = arg.toString();
        }
        if(type.equals("ActiveGame")){
            System.out.println(MainModel.get().getGame().getActiveGame());
        }

        switch (type){
            case "ErrorMessage":
                runOnUI(new Runnable() {
                    @Override
                    public void run() {
                        mapActivity.popToast(MainModel.get().getErrorMessage());
                    }
                });
            case "Player":
                updatePlayer();
                break;
            case "Store":
                updateStore();

                break;
            case "TrainCardColor":
                updatePlayerTrainCards();
                updateTrainDeck();
                break;

            case"DestinationCard":
                updatePlayerDestCards();
                updateDestDeck();
                break;

            case "ActiveGame":
                updateActiveGame();
////                mapActivity.updateGameInfo((ActiveGame)arg);
//                ActiveGame ag = MainModel.get().getGame().getActiveGame();
//                mapActivity.updateTurnView(ag.getActivePlayerInd(), ag.getPlayers());
                break;
            case "MapModel":
                updateMap();
                break;
            case "ChatQueue":
                updateChat();
                break;
            default:
                Log.d(TAG, "Type " +type +" is not being checked");
        }
    }

    private void updatePlayerTrainCards(){
        runOnUI(new Runnable() {
            @Override
            public void run() {
                mapActivity.updateTrainCards(MainModel.get().getPlayer());
            }
        });
    }

    private void updatePlayerDestCards(){
        runOnUI(new Runnable() {
            @Override
            public void run() {
                mapActivity.updateDestinationCards(MainModel.get().getPlayer().getDestHand());
            }
        });
    }

    private void updatePlayer(){
        runOnUI(new Runnable() {
            @Override
            public void run() {
                mapActivity.updateTrainCards(MainModel.get().getPlayer());
            }
        });
    }

    private void updateStore(){
        runOnUI(new Runnable() {
            @Override
            public void run() {
                mapActivity.updateStore(MainModel.get().getStore());
            }
        });
    }

    private void updateActiveGame(){
        runOnUI(new Runnable() {
            @Override
            public void run() {
                mapActivity.updateGameInfo(MainModel.get().getGame().getActiveGame());

                ActiveGame ag = MainModel.get().getGame().getActiveGame();
                mapActivity.updateTurnView(ag.getActivePlayerInd(), ag.getPlayers());
            }
        });
    }

    private void updateMap(){
        runOnUI(new Runnable() {
            @Override
            public void run() {
                mapActivity.updateMap(MainModel.get().getMapModel());
            }
        });
    }

    private void updateChat(){
        runOnUI(new Runnable() {
            @Override
            public void run() {
                mapActivity.updateChat(MainModel.get().getGame().getActiveGame().getChatQueue());
            }
        });
    }

    private void updateTrainDeck(){
        MainModel.get().getGame().getActiveGame().updateTrainDeckSize();
        runOnUI(new Runnable() {
            @Override
            public void run() {
                mapActivity.updateTrainDeck(MainModel.get().getGame().getActiveGame().getTrainDeckSize());
            }
        });
    }

    private void updateDestDeck(){
        MainModel.get().getGame().getActiveGame().updateDestDeckSize();
        runOnUI(new Runnable() {
            @Override
            public void run() {
                mapActivity.updateDestDeck(MainModel.get().getGame().getActiveGame().getDestDeckSize());
            }
        });
    }

    public void drawDestination(){
        MainModel.get().getGame().getActiveGame().setDestDeckSize(3);
//        Service drawDestinationService = new DrawDestCardService();
//        drawDestinationService.
    }
    public void drawStore(int index){

        Service drawStoreService = null;

    }
    public void drawTrainCard() {
        MainModel.get().getGame().getActiveGame().setTrainDeckSize(3);
        Service drawTrainCardService = null;
    }
    public void selectCity(float x, float y,PointF size){
        for(City city : City.values()){
            PointF point = MapEquations.getPoint(city,size);
            float dist = (float)Math.pow(Math.pow(x-point.x,2) + Math.pow(y-point.y,2),.5);

            if(dist < 50){
                if(MainModel.get().getMapModel().getSelectedCity() == null) selectCity(city);
                else if(MainModel.get().getMapModel().getSelectedCity().equals(city)) deselectCity();
                else claimRoute(MainModel.get().getMapModel().getSelectedCity(),city);
                return;
            }
        }
        deselectCity();
    }
    private void claimRoute(City city1, City city2){
        MainModel.get().getGame().getActiveGame().setRouteOwner(city1,city2);
        MainModel.get().getMapModel().setSelectedCity(null);
        MainModel.get().getPlayer().decrementPiecesLeft(5);
        drawDestination();
        drawTrainCard();
        TrainCardColor[] trainCardColors ={TrainCardColor.BLACK,TrainCardColor.BLACK,TrainCardColor.BLACK,TrainCardColor.BLACK,TrainCardColor.BLACK};
        MainModel.get().getGame().getActiveGame().setStore(new Store(trainCardColors));
        advanceTurn();
        MainModel.get().getPlayer().addToDestHand(DestinationCard.CHICAGO_ORLEANS);
        MainModel.get().getPlayer().addToDestHand(DestinationCard.BOSTON_MIAMI);
        MainModel.get().getPlayer().addToDestHand(DestinationCard.DENV_ELPASO);

        MainModel.get().getGame().getActiveGame().doStuff();


        //TODO Send prompt to view with message "Claim route between %city1 and %city2?" (place names in for city1 and city2)
        //TODO Prompt should also include "Requires %numberRequired %color train car cards"
        //TODO Prompt includes "You have %numberOwned %color train car cards"
        //TODO Prompt may also include "You must use %numberRequired - numberOwned rainbow car cards"
        //TODO Options for confirm and deny
        //TODO (Next parts will not be called from here, but will include the sequence of events)
        //TODO If confirmed, call confirmRoute()
        //TODO If deny, deselectCity()
    }

    public void confirmRoute(){
        Service claimRouteService = new ClaimRouteService();
    }

    private void selectCity(City city){
        MainModel.get().getMapModel().setSelectedCity(city);
    }

    private void deselectCity(){
        MainModel.get().getMapModel().setSelectedCity(null);
    }
    public void sendMessage(String message){
        if(message.length() == 0) return;

        Service chatService = new ChatService();
        chatService.connectToProxy(message,MainModel.get().getGame().getActiveGame().getName());

    }
    private void advanceTurn(){
        ActiveGame game = MainModel.get().getGame().getActiveGame();
        game.incActivePlayerInd();
        mapActivity.updateTurnView(game.getActivePlayerInd(), game.getPlayers());
    }

    public void initActionBar(){
        ActiveGame ag = MainModel.get().getGame().getActiveGame();
        mapActivity.updateTurnView(ag.getActivePlayerInd(), ag.getPlayers());
        mapActivity.updateStore(ag.getStore());
    }

    public void showDestDialog(){
        EnumSet<DestinationCard> destHand = MainModel.get().getPlayer().getDestHand();
        this.mapActivity.setDialogInfo(destHand);
    }

    public void checkDestination(boolean isChecked, int index){
        DestinationCard card = null;
        EnumSet<DestinationCard> hand = MainModel.get().getPlayer().getDestHand();
        int i = 0;
        for(DestinationCard itCard : hand){
            if(i == index) card = itCard;
            i += 1;
        }
        if(isChecked) selectedDestCards.add(card);
        if(!isChecked) selectedDestCards.remove(card);
    }

    public boolean enoughDestsSelected(){
        if(selectedDestCards.size() > 1) return true;
        return false;
    }

    public void clickDialogAccept(){
        MainModel.get().getPlayer().setDestHand(selectedDestCards);
//        updateActiveGame();
    }

    private void runOnUI(Runnable run){
        mapActivity.runOnUiThread(run);
    }
}
