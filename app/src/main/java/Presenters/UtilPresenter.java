package Presenters;

import Models.MainModel;
import views.activities.MapActivity;

public class UtilPresenter {

    public static void runOnUI(MapActivity activity, Runnable run) {
//        TODO Each presenter will still need a reference to the activity to do "runOnUiThread"
        activity.runOnUiThread(run);
    }

    public static void deselectCity(){
        MainModel.get().getMapModel().setSelectedCity(null);
    }
    public static void deselectRoute(){
        MainModel.get().getMapModel().setSelectedRoute(null);
    }
}
