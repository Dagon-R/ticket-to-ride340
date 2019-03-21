package Presenters;

import views.activities.MapActivity;

public class UtilPresenter {

    public static void runOnUI(MapActivity activity, Runnable run) {
//        TODO Each presenter will still need a reference to the activity to do "runOnUiThread"
        activity.runOnUiThread(run);
    }
}
