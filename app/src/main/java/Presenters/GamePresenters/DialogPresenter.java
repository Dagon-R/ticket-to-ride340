package Presenters.GamePresenters;

import android.app.Dialog;
import android.util.Log;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Observable;
import java.util.Observer;

import Models.MainModel;
import Phase2Models.DestinationCard;
import Phase3Services.DrawDestService;
import Phase3Services.DrawTrainsService;
import Phase3Services.ClaimRouteService;
import Presenters.UtilPresenter;
import Services.Service;
import views.DialogLogic;
import views.activities.MapActivity;

public class DialogPresenter implements Observer {
    static String TAG = "DialogPresenter";
    ArrayList<DestinationCard> selectedDestCards;
    MapActivity activity;
    DialogLogic logic;

    public DialogPresenter(MapActivity activity, DialogLogic logic) {
        this.logic = logic;
        this.activity = activity;
        MainModel.get().addDialogObservers(this);
        showDestDialog();
        //TODO attach to MapModel,Active Game?
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

        switch (type){
            case "ConfirmRoute":
                confirmRouteDialog();
                break;
            default:
                Log.d(TAG, "Type " + type + " is not being checked");
        }


    }

    private void confirmRouteDialog(){
        if(MainModel.get().getMapModel().getSelectedRoute() == null) return;
        UtilPresenter.runOnUI(activity, new Runnable() {
            @Override
            public void run() {

                logic.showConfirmRouteDialog(MainModel.get().getMapModel(), MainModel.get().getPlayer());
            }
        });
    }
    public void deselectRoute() {
        MainModel.get().getMapModel().setSelectedRoute(null);
    }
    public void deselectCity() {
        MainModel.get().getMapModel().setSelectedCity(null);
    }
    public void confirmRoute(){
        //Confirm route after being prompted
        Service claimRouteService = new ClaimRouteService();
        //TODO Connect to Proxy

    }

    ////////////////////Choose dest dialog///////////////////////////////

    public void showDestDialog() {

        UtilPresenter.runOnUI(activity, new Runnable() {
            @Override
            public void run() {
                EnumSet<DestinationCard> destHand = MainModel.get().getPlayer().getDestHand();
                logic.showDestDialog(destHand);
            }
        });
    }

    public boolean enoughDestsSelected() {
        return selectedDestCards.size() > 1;
    }

    public void clickDestDialogAccept() {
        MainModel.get().getPlayer().setDestHand(selectedDestCards);
//        updateActiveGame();
    }

    public void checkDestination(boolean isChecked, int index) {
        DestinationCard card = null;
        EnumSet<DestinationCard> hand = MainModel.get().getPlayer().getDestHand();
        int i = 0;
        for (DestinationCard itCard : hand) {
            if (i == index) card = itCard;
            i += 1;
        }
        if (isChecked) selectedDestCards.add(card);
        if (!isChecked) selectedDestCards.remove(card);
    }


    //////////////////Draw Train Card Dialog////////////////////////////
    public void showDrawTrainDialog() {
        UtilPresenter.runOnUI(activity, new Runnable() {
            @Override
            public void run() {
                logic.showTrainDialog();
            }
        });
    }

    public void clickTrainAccept(){
        //call trainCard service
        Service drawTrainService = new DrawTrainsService();
        drawTrainService.connectToProxy();
        //TODO: may need a callback to update trainDeck size
    }


    //////////////////Draw Dest Card Confirm Dialog////////////////////////////
    public void showDrawDestDialog(){
        UtilPresenter.runOnUI(activity, new Runnable() {
            @Override
            public void run() {
                logic.showDrawDestDialog();
            }
        });
    }

    public void clickDrawDestAccept(){
        //call trainCard service
        //Service drawDestService = new DrawDestCardService();
        //drawDestService.connectToProxy();
        //TODO: make sure destDialog is triggered after drawdestcardserice
    }



}
