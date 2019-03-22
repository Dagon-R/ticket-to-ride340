package Presenters.GamePresenters;

import java.util.Observable;
import java.util.Observer;

import Models.ActiveGame;
import Models.MainModel;
import Presenters.UtilPresenter;
import views.activities.MapActivity;

public class GameInfoPresenter implements Observer {
    MapActivity activity;

    public GameInfoPresenter(MapActivity activity) {
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
        switch (type){
            case "ActiveGame":
                updateActiveGame();
                break;
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
