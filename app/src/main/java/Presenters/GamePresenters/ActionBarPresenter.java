package Presenters.GamePresenters;

import android.util.Log;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import Models.ActiveGame;
import Models.MainModel;
import Presenters.UtilPresenter;
import Services.Service;
import States.IActionBarPresenter;
import views.ActionBarLogic;
import views.DialogLogic;
import views.ViewInterfaces.ActionBar;
import views.activities.MapActivity;

public class ActionBarPresenter implements Observer, ActionBar, IActionBarPresenter {
    private static String TAG = "ActionBarPresenter";
    private MapActivity mapActivity;
    private ActionBarLogic actionBarLogic;

    public ActionBarPresenter(MapActivity activity, ActionBarLogic actionBarLogic) {
        this.mapActivity = activity;
        this.actionBarLogic = actionBarLogic;
        MainModel.get().addActionBarObservers(this);
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
                //TODO Link up properly
                updateTurn();
            default:
                Log.d(TAG, "Type " + type + " is not being checked");
        }
    }


    @Override
    public void drawStore(View view, int i) {
        //service called on accept dialog (dialogPresenter))
        Service drawDestinationCardServe = null;
        Log.d(TAG, "Attach draw Store Service");
    }

    @Override
    public void drawDestinationCard(View view) {
        //show confirm dialog
        DialogPresenter dp = new DialogPresenter(mapActivity, new DialogLogic(mapActivity));
        dp.showDestDialog();
        //service called on accept dialog (dialogPresenter)
        //Service drawDestinationCardServe = null;
        Log.d(TAG, "Attach draw Destination Service");
        //pop confirm dialog
    }

    public void drawTrainCarCard(View view) {
        //show confirm dialog
        DialogPresenter dp = new DialogPresenter(mapActivity, new DialogLogic(mapActivity));
        dp.showDrawTrainDialog();
        //service called on accept dialog (dialogPresenter)
        //Service drawTrainCardService = null;
        Log.d(TAG, "Attach draw Train Car Card Service");
    }

    private void updateTrainDeck() {
        UtilPresenter.runOnUI(mapActivity,new Runnable() {
            @Override
            public void run() {
                actionBarLogic.updateTrainDeck(MainModel.get().getGame().getActiveGame().getTrainDeckSize());
            }
        });
    }

    private void updateTurn() {
        UtilPresenter.runOnUI(mapActivity,new Runnable() {
            @Override
            public void run() {
                ActiveGame ag = MainModel.get().getGame().getActiveGame();
                actionBarLogic.updateTurnView(ag.getActivePlayerInd(), ag.getPlayers());
            }
        });
    }

    private void updateDestDeck() {
//        MainModel.get().getGame().getActiveGame().updateDestDeckSize();
        UtilPresenter.runOnUI(mapActivity,new Runnable() {
            @Override
            public void run() {
                actionBarLogic.updateDestDeck(MainModel.get().getGame().getActiveGame().getDestDeckSize());
            }
        });
    }

    private void updateStore() {
        UtilPresenter.runOnUI(mapActivity,new Runnable() {
            @Override
            public void run() {
                actionBarLogic.updateStore(MainModel.get().getStore());
            }
        });
    }

}
