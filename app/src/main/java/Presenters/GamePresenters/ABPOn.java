package Presenters.GamePresenters;

import android.util.Log;
import android.view.View;

import Services.Service;
import States.IActionBarPresenter;
import views.DialogLogic;
import views.activities.MapActivity;

public class ABPOn implements IActionBarPresenter {
    private static String TAG = "ActionBarPresenter";
    private MapActivity mapActivity;

    ABPOn(MapActivity activity) { mapActivity = activity; }

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

    @Override
    public void drawTrainCarCard(View view) {
        //show confirm dialog
        DialogPresenter dp = new DialogPresenter(mapActivity, new DialogLogic(mapActivity));
        dp.showDrawTrainDialog();
        //service called on accept dialog (dialogPresenter)
        //Service drawTrainCardService = null;
        Log.d(TAG, "Attach draw Train Car Card Service");
    }
}
