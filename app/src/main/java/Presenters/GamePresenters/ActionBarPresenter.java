package Presenters.GamePresenters;

import android.util.Log;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import Models.ActiveGame;
import Models.MainModel;
import Models.PlayerColorEnum;
import Presenters.UtilPresenter;
import States.IActionBarPresenter;
import views.ActionBarLogic;
import views.ViewInterfaces.ActionBar;
import views.activities.MapActivity;

public class ActionBarPresenter implements Observer, ActionBar, IActionBarPresenter {
    private MapActivity mapActivity;
    private ActionBarLogic actionBarLogic;
    private IActionBarPresenter turnLogic;
    private static String TAG = "ActionBarPresenter";

    public ActionBarPresenter(MapActivity activity, ActionBarLogic actionBarLogic) {
        this.mapActivity = activity;
        this.actionBarLogic = actionBarLogic;
        ActiveGame game = MainModel.get().getGame().getActiveGame();
        if (game.getPlayer().getColor() == PlayerColorEnum.values()[0])
        { this.turnLogic = new ABPOn(activity); }
        else {this.turnLogic = new ABPOff();}
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
        turnLogic.drawStore(view,i);
    }

    @Override
    public void drawDestinationCard(View view) {
        turnLogic.drawDestinationCard(view);
    }

    public void drawTrainCarCard(View view) {
        turnLogic.drawTrainCarCard(view);
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
        ActiveGame game = MainModel.get().getGame().getActiveGame();
        int activeIndex = game.getActivePlayerInd();
        if (PlayerColorEnum.values()[activeIndex] == game.getPlayer().getColor())
        { turnLogic = new ABPOn(mapActivity); }
        else { turnLogic = new ABPOff(); }
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
