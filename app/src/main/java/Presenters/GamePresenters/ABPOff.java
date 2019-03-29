package Presenters.GamePresenters;

import android.util.Log;
import android.view.View;

import States.IActionBarPresenter;

public class ABPOff implements IActionBarPresenter {
    private static String TAG = "ActionBarPresenter";
    @Override
    public void drawStore(View view, int i) {
        Log.d(TAG, "Tried to draw from an inactive store");
    }

    @Override
    public void drawDestinationCard(View view) {
        Log.d(TAG, "Tried to draw from an inactive destination deck");
    }

    @Override
    public void drawTrainCarCard(View view) {
        Log.d(TAG, "Tried to draw from an inactive train card deck");
    }
}
