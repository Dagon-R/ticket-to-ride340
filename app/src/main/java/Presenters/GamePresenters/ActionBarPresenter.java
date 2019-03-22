package Presenters.GamePresenters;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import Models.ActiveGame;
import Models.MainModel;
import Presenters.UtilPresenter;
import Services.Service;
import views.ActionBarLogic;
import views.ViewInterfaces.ActionBar;
import views.activities.MapActivity;

public class ActionBarPresenter implements Observer, ActionBar {
    private static String TAG = "ActionBarPresenter";
    private MapActivity mapActivity;
    private ActionBarLogic actionBarLogic;

    public ActionBarPresenter(MapActivity activity, ActionBarLogic actionBarLogic) {
        this.mapActivity = activity;
        this.actionBarLogic = actionBarLogic;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(mapActivity == null){
            System.out.println("ERROR: Map activity is not set");
        }
        String type = o.getClass().getSimpleName();
        if (arg != null) {
            type = arg.getClass().getSimpleName();
            if (type.equals("String")) {
                type = arg.toString();
            }
        }

        switch (type){
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
            default:
                Log.d(TAG, "Type " + type + " is not being checked");
        }
    }


    @Override
    public void drawStore(int i) {
        Service drawDestinationCardServe = null;
        Log.d(TAG, "Attach draw Store Service");
    }

    @Override
    public void drawDestinationCard() {
        Service drawDestinationCardServe = null;
        Log.d(TAG, "Attach draw Destination Service");
    }

    public void drawTrainCarCard() {
        Service drawTrainCardService = null;
        Log.d(TAG, "Attach draw Train Car Card Service");
    }

    private void updateTrainDeck() {
        UtilPresenter.runOnUI(mapActivity,new Runnable() {
            @Override
            public void run() {
//                mapActivity.updateTrainDeck(MainModel.get().getGame().getActiveGame().getTrainDeckSize());
            }
        });
    }

    private void updateTurn() {
        UtilPresenter.runOnUI(mapActivity,new Runnable() {
            @Override
            public void run() {
                ActiveGame ag = MainModel.get().getGame().getActiveGame();
//                mapActivity.updateTurnView(ag.getActivePlayerInd(), ag.getPlayers());
            }
        });
    }

    private void updateDestDeck() {
//        MainModel.get().getGame().getActiveGame().updateDestDeckSize();
        UtilPresenter.runOnUI(mapActivity,new Runnable() {
            @Override
            public void run() {
//                mapActivity.updateDestDeck(MainModel.get().getGame().getActiveGame().getDestDeckSize());
            }
        });
    }

    private void updateStore() {
        UtilPresenter.runOnUI(mapActivity,new Runnable() {
            @Override
            public void run() {
//                mapActivity.updateStore(MainModel.get().getStore());
            }
        });
    }

}
