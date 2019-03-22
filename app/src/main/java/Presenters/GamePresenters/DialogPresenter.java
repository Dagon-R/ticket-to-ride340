package Presenters.GamePresenters;

import android.util.Log;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Observable;
import java.util.Observer;

import Models.MainModel;
import Phase2Models.DestinationCard;
import Presenters.UtilPresenter;
import views.activities.MapActivity;

public class DialogPresenter implements Observer {
    static String TAG = "DialogPresenter";
    ArrayList<DestinationCard> selectedDestCards;
    MapActivity activity;

    public DialogPresenter(MapActivity activity) {
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
            case "ErrorMessage":
                UtilPresenter.runOnUI(activity,new Runnable() {
                    @Override
                    public void run() {
                        activity.popToast(MainModel.get().getErrorMessage());
                    }
                });
                break;
            case "MapModel":
                confirmRouteDialog();
                break;
            default:
                Log.d(TAG, "Type " + type + " is not being checked");
        }


    }

    private void confirmRouteDialog(){
        System.out.println("Need to Link To Dialog");
        UtilPresenter.runOnUI(activity, new Runnable() {
            @Override
            public void run() {
                //
            }
        });
    }

    public void showDestDialog() {

        UtilPresenter.runOnUI(activity, new Runnable() {
            @Override
            public void run() {
                EnumSet<DestinationCard> destHand = MainModel.get().getPlayer().getDestHand();
                //this.mapActivity.setDialogInfo(destHand);
            }
        });
    }

    public boolean enoughDestsSelected() {
        return selectedDestCards.size() > 1;
    }

    public void clickDialogAccept() {
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







}
