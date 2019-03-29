package Presenters.GamePresenters;

import android.util.Log;
import android.view.View;

import States.IActionBarPresenter;
import views.activities.MapActivity;

public class ABPOff implements IActionBarPresenter {
    private static String TAG = "ActionBarPresenter";
    private MapActivity activity;
    ABPOff(MapActivity mapActivity) {this.activity = mapActivity;}
    @Override
    public void drawStore(View view, int i) {
        String STORE_ERROR = "Tried to draw from an inactive store";
        activity.popToast(STORE_ERROR);
        Log.d(TAG, STORE_ERROR);
    }

    @Override
    public void drawDestinationCard(View view) {
        String DEST_ERROR = "Tried to draw from an inactive destination deck";
        activity.popToast(DEST_ERROR);
        Log.d(TAG, DEST_ERROR);
    }

    @Override
    public void drawTrainCarCard(View view) {
        String TRAIN_ERROR = "Tried to draw from an inactive train card deck";
        activity.popToast(TRAIN_ERROR);
        Log.d(TAG, TRAIN_ERROR);
    }
}
