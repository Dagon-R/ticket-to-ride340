package Presenters.GamePresenters;
import android.graphics.PointF;
import android.util.Log;
import java.util.Observable;
import java.util.Observer;

import Models.ActiveGame;
import Models.MainModel;
import Phase2Models.City;

import Phase2Models.Route;
import Phase3Services.ClaimRouteService;
import Presenters.MapEquations;
import Presenters.UtilPresenter;
import Services.Service;
import States.IMapPresenter;
import views.MapLogic;
import views.ViewInterfaces.IMap;
import views.activities.MapActivity;

public class MapPresenter implements Observer, IMap, IMapPresenter {
    static String TAG = "Presenter";
    MapActivity mapActivity;
    MapLogic logic;

    public MapPresenter(MapActivity mapActivity, MapLogic logic) {
        this.mapActivity = mapActivity;
        this.logic = logic;
        MainModel.get().addMapObservers(this);
        updateMap();

    }


    @Override
    public void update(Observable o, Object arg) {
        String type = o.getClass().getSimpleName();
        if (arg != null) {
            type = arg.getClass().getSimpleName();
        }
        if (type.equals("String")) {
            type = arg.toString();
        }


        switch (type) {
            case "ErrorMessage":
                UtilPresenter.runOnUI(mapActivity,new Runnable() {
                    @Override
                    public void run() {
                        mapActivity.popToast(MainModel.get().getErrorMessage());
                    }
                });
                break;
            case "MapModel":
                updateMap();
                break;

            default:
                Log.d(TAG, "Type " + type + " is not being checked");
        }
    }

//    private void updatePlayerTrainCards() {
//        System.out.println("Migrated to PlayerInfoPresenter");
//    }
//
//    private void updatePlayerDestCards() {
//        System.out.println("Migrated to PlayerInfoPresenter");
//    }
//
//
//
//    private void updateStore() {
//        System.out.println("Migrated to Action Bar Presenter");
//    }
//
//    private void updateActiveGame() {
//        System.out.println("Migrated to GameInfoPresenter and ActionBarPresenter");
//
//    }

    private void updateMap() {
        Log.d(TAG, "updateMap method: THERE MAY BE AN ISSUE HERE");
        UtilPresenter.runOnUI(mapActivity,new Runnable() {
            @Override
            public void run() {
                logic.updateMap(MainModel.get().getMapModel());
            }
        });
    }

//    private void updateChat() {
//        System.out.println("Migrated to ChatSheetPresenter");
//    }
//
//    private void updateTrainDeck() {
//        System.out.println("Migrated to ActionBarPresenter");
//    }
////
//    private void updateDestDeck() {
//        System.out.println("Migrated to ActionBarPresenter");
//    }
//
//
//
//    public void drawStore(int index) {
//        System.out.println("Migrated to ActionBarPresenter");
//    }
//
//    public void drawTrainCard() {
//        System.out.println("Migrated to ActionBarPresenter");
//    }

    public void selectCity(float x, float y, PointF size) {
        System.out.println("selectCity: " +"Attempting to select");
        for (City city : City.values()) {
            PointF point = MapEquations.getPoint(city, size);
            float dist = (float) Math.pow(Math.pow(x - point.x, 2) + Math.pow(y - point.y, 2), .5);

            if (dist < 50) {
                if (MainModel.get().getMapModel().getSelectedCity() == null) selectCity(city);
                else if (MainModel.get().getMapModel().getSelectedCity().equals(city))
                    UtilPresenter.deselectCity();
                else claimRoute(MainModel.get().getMapModel().getSelectedCity(), city);
                return;
            }
        }
        UtilPresenter.deselectCity();
    }

    private void claimRoute(City city1, City city2) {

        Route route = Route.getRoute(city1,city2);
        if(route == null){
            UtilPresenter.deselectCity();
            return;
        }
        ActiveGame game = MainModel.get().getGame().getActiveGame();
        if(route.isDouble()){

            if(game.getPlayers().size()<4){

                if(game.getRouteOwners(route) == null){
                    MainModel.get().getMapModel().setSelectedRoute(route);
                    return;
                }
            }
            MainModel.get().getMapModel().setSelectedRoute(route);
            return;

        }else if(game.getRouteOwners(route)[0] == null){
            MainModel.get().getMapModel().setSelectedRoute(route);
        }

    }



    private void selectCity(City city) {
        MainModel.get().getMapModel().setSelectedCity(city);
    }


    @Override
    public void mapClick(float x, float y, PointF size) {
        System.out.println("CLICKING!!!!");
        selectCity(x,y,size);
    }


    //    private void advanceTurn() {
//        ActiveGame game = MainModel.get().getGame().getActiveGame();
//        game.incActivePlayerInd();
//        mapActivity.updateTurnView(game.getActivePlayerInd(), game.getPlayers());
//    }

//    public void initActionBar() {
//        System.out.println("Migrated to ActionBarPresenter");
//    }
//
//    public void showDestDialog() {
//        System.out.println("Migrated to DiologPresenter");
//    }
//
//    public void checkDestination(boolean isChecked, int index) {
//        System.out.println("Migrated to DiologPresenter");
//    }
//
//    public boolean enoughDestsSelected() {
//        System.out.println("Migrated to DiologPresenter");
//        return false;
//    }
//
//    public void clickDialogAccept() {
//        System.out.println("Migrated to DiologPresenter");
//    }

}
