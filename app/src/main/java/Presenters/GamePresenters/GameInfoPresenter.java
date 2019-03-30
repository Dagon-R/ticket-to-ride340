package Presenters.GamePresenters;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import Models.ActiveGame;
import Models.MainModel;
import Presenters.UtilPresenter;
import views.activities.MapActivity;

public class GameInfoPresenter implements Observer {
    static String TAG = "GameInfoPresenter";
    MapActivity activity;

    public GameInfoPresenter(MapActivity activity) {
        this.activity = activity;
        MainModel.get().addGameInfoObservers(this);
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
            case "ActiveGame":
                updateActiveGame();
                break;
            default:
                Log.d(TAG, "Type " + type + " is not being checked");
        }
    }

    private void updateActiveGame() {
        UtilPresenter.runOnUI(activity,new Runnable() {
            @Override
            public void run() {
//                mapActivity.updateGameInfo(MainModel.get().getGame().getActiveGame());

//                ActiveGame ag = MainModel.get().getGame().getActiveGame();
//                mapActivity.updateTurnView(ag.getActivePlayerInd(), ag.getPlayers());
            }
        });
    }


}
