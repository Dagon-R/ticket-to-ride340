package views;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import Models.ThisPlayer;
import Phase2Models.MapModel;
import Presenters.GamePresenters.DialogPresenter;
import views.activities.MapActivity;

public class DialogLogic {
    DialogPresenter dialogPresenter;
    MapActivity mapActivity;
    View.OnClickListener confirmRoute = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            dialogPresenter.confirmRoute();
            dialogPresenter.deselectCity();
            dialogPresenter.deselectRoute();
        }
    };
    View.OnClickListener rejectRoute = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialogPresenter.deselectCity();
            dialogPresenter.deselectRoute();
        }
    };
    public DialogLogic(MapActivity mapActivity) {
        dialogPresenter = new DialogPresenter(mapActivity,this);
        this.mapActivity = mapActivity;
    }

    public void showConfirmRouteDialog(MapModel mapModel, ThisPlayer player){
        final Dialog dialog = new Dialog(mapActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.claim_route);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        setConfirmText(mapModel,player);
        //TODO FINISH LINKING

    }

    private void setConfirmText(MapModel model,ThisPlayer player){
        mapActivity.findViewById(R.id.selectDouble).setVisibility(View.GONE);
        mapActivity.findViewById(R.id.doubleRoute).setVisibility(View.GONE);
//        mapActivity.findViewById(R.id.singleRoute).setBackgroundT;
        //Add Click Listeners
        mapActivity.findViewById(R.id.confirmRoute).setOnClickListener(confirmRoute);
        mapActivity.findViewById(R.id.rejectRoute).setOnClickListener(rejectRoute);

        if(model.getSelectedRoute().isDouble()){
            mapActivity.findViewById(R.id.selectDouble).setVisibility(View.VISIBLE);
            mapActivity.findViewById(R.id.doubleRoute).setVisibility(View.VISIBLE);
        }
        ((TextView)mapActivity.findViewById(R.id.city1)).setText(model.getSelectedRoute().getCity1().getName());
        ((TextView)mapActivity.findViewById(R.id.city1)).setText(model.getSelectedRoute().getCity2().getName());


    }

    public void showDestinationDialog(){

    }

    public void showEndGameDialog(){

    }
}
