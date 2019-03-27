package views;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import Phase2Models.MapModel;
import Presenters.GamePresenters.DialogPresenter;
import views.activities.MapActivity;

public class DialogLogic {
    DialogPresenter dialogPresenter;
    MapActivity mapActivity;
    public DialogLogic(MapActivity mapActivity) {
        dialogPresenter = new DialogPresenter(mapActivity,this);
        this.mapActivity = mapActivity;
    }

    public void showConfirmRouteDialog(MapModel mapModel){
        final Dialog dialog = new Dialog(mapActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.claim_route);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        setConfirmText(mapModel);
        //TODO FINISH LINKING
        //TODO If confirmed, call confirmRoute()
        //TODO If deny, deselectCity()
    }

    private void setConfirmText(MapModel model){
        mapActivity.findViewById(R.id.selectDouble).setVisibility(View.GONE);
        if(model.getSelectedRoute().isDouble()){
            mapActivity.findViewById(R.id.selectDouble).setVisibility(View.VISIBLE);
        }
        ((TextView)mapActivity.findViewById(R.id.city1)).setText(model.getSelectedRoute().getCity1().getName());
        ((TextView)mapActivity.findViewById(R.id.city1)).setText(model.getSelectedRoute().getCity2().getName());


    }

    public void showDestinationDialog(){

    }

    public void showEndGameDialog(){

    }
}
