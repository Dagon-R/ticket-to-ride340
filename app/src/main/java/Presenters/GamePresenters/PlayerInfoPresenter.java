package Presenters.GamePresenters;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import Models.MainModel;
import Presenters.UtilPresenter;
import views.activities.MapActivity;

public class PlayerInfoPresenter implements Observer {
    private static String TAG = "PlayerInfoPresenter";
    MapActivity activity;

    public PlayerInfoPresenter(MapActivity activity) {
        this.activity = activity;
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
                UtilPresenter.runOnUI(activity,new Runnable() {
                    @Override
                    public void run() {
                        activity.popToast(MainModel.get().getErrorMessage());
                    }
                });
                break;
            case "DestinationCard":
                updatePlayerDestCards();
                break;
            case "TrainCardColor":
                updatePlayerTrainCards();
                break;
            default:
                Log.d(TAG, "Type " + type + " is not being checked");
        }
    }


    private void updatePlayerDestCards() {
        UtilPresenter.runOnUI(activity,new Runnable() {
            @Override
            public void run() {
//                mapActivity.updateDestinationCards(MainModel.get().getPlayer().getDestHand());
            }
        });
    }

    private void updatePlayerTrainCards() {
        UtilPresenter.runOnUI(activity,new Runnable() {
            @Override
            public void run() {
//                mapActivity.updateTrainCards(MainModel.get().getPlayer());
            }
        });
    }


}
