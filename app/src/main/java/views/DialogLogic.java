package views;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import Models.ThisPlayer;
import java.util.ArrayList;
import java.util.EnumSet;

import Phase2Models.DestinationCard;
import Phase2Models.MapModel;
import Presenters.GamePresenters.DialogPresenter;
import views.activities.MapActivity;

public class DialogLogic {
    DialogPresenter dialogPresenter;
    MapActivity mapActivity;
    final Dialog dialog;
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
        this.mapActivity = mapActivity;
        dialog = new Dialog(mapActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogPresenter = new DialogPresenter(mapActivity,this);


    }

    public void showConfirmRouteDialog(MapModel mapModel, ThisPlayer player){


        dialog.setContentView(R.layout.claim_route);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        setConfirmText(mapModel,player);
        dialog.show();
        //TODO FINISH LINKING

    }

    private void setConfirmText(MapModel model,ThisPlayer player){
//        mapActivity.findViewById(R.id.selectDouble).setVisibility(View.GONE);
//        mapActivity.findViewById(R.id.doubleRoute).setVisibility(View.GONE);
////        mapActivity.findViewById(R.id.singleRoute).setBackgroundColor();
//        //Add Click Listeners
//        mapActivity.findViewById(R.id.confirmRoute).setOnClickListener(confirmRoute);
//        mapActivity.findViewById(R.id.rejectRoute).setOnClickListener(rejectRoute);
//
//        if(model.getSelectedRoute().isDouble()){
//            mapActivity.findViewById(R.id.selectDouble).setVisibility(View.VISIBLE);
//            mapActivity.findViewById(R.id.doubleRoute).setVisibility(View.VISIBLE);
//        }
//        ((TextView)mapActivity.findViewById(R.id.city1)).setText(model.getSelectedRoute().getCity1().getName());
//        ((TextView)mapActivity.findViewById(R.id.city1)).setText(model.getSelectedRoute().getCity2().getName());


    }


    ///////////////////////Initial Destination Dialog//////////////////////////////
    //pass destination

    public void showDestDialog(EnumSet<DestinationCard> destHand){
        dialog.setContentView(R.layout.dest_card_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        setDestDialogText(dialog, destHand);

        setDestDialogListeners(dialog);

        //make dialog only cover 85% of screen
        DisplayMetrics displayMetrics = mapActivity.getResources().getDisplayMetrics();
        int dialogWidth = (int)(displayMetrics.widthPixels * 0.85);
        int dialogHeight = (int)(displayMetrics.heightPixels * 0.85);
        dialog.getWindow().setLayout(dialogWidth, dialogHeight);

        dialog.show();

    }

    //listeners for buttons and checkboxes on destination dialog
    private void setDestDialogListeners(final Dialog dialog){
        final Button acceptButton = (Button) dialog.findViewById(R.id.acceptDestCards);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicked button!");
                dialogPresenter.clickDestDialogAccept();
                dialog.dismiss();
            }
        });

        CheckBox card1Check = (CheckBox) dialog.findViewById(R.id.dest1Check);
        card1Check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dialogPresenter.checkDestination(isChecked, 0);
                if(dialogPresenter.enoughDestsSelected()) acceptButton.setEnabled(true);
                else acceptButton.setEnabled(false);
            }
        });

        CheckBox card2Check = (CheckBox) dialog.findViewById(R.id.dest2Check);
        card2Check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dialogPresenter.checkDestination(isChecked, 1);
                if(dialogPresenter.enoughDestsSelected()) acceptButton.setEnabled(true);
                else acceptButton.setEnabled(false);
            }
        });

        CheckBox card3Check = (CheckBox) dialog.findViewById(R.id.dest3Check);
        card3Check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dialogPresenter.checkDestination(isChecked, 2);
                if(dialogPresenter.enoughDestsSelected()) acceptButton.setEnabled(true);
                else acceptButton.setEnabled(false);
            }
        });

    }

    private void setDestDialogText(Dialog dialog, EnumSet<DestinationCard> destHand){
        ArrayList<TextView> destViews = new ArrayList<>();
        destViews.add((TextView) dialog.findViewById(R.id.dests1));
        destViews.add((TextView) dialog.findViewById(R.id.dests2));
        destViews.add((TextView) dialog.findViewById(R.id.dests3));

        ArrayList<TextView> valViews = new ArrayList<>();
        valViews.add((TextView) dialog.findViewById(R.id.val1));
        valViews.add((TextView) dialog.findViewById(R.id.val2));
        valViews.add((TextView) dialog.findViewById(R.id.val3));

        int i = 0;
        String destText = "";
        for(DestinationCard card : destHand){
            destText = card.getFirstCityName() + " - " + card.getSecondCityName();
            destViews.get(i).setText(destText);
            valViews.get(i).setText(Integer.toString(card.getValue()));
            i += 1;
        }
    }



    //////////////////////////Draw train confirm Dialog//////////////////////////////////////
    public void showTrainDialog(){
        final Dialog dialog = new Dialog(mapActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.draw_train_dialog); //TODO: change to new layout
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        setTrainDialogListeners(dialog);

        //make dialog only cover 85% of screen
        DisplayMetrics displayMetrics = mapActivity.getResources().getDisplayMetrics();
        int dialogWidth = (int)(displayMetrics.widthPixels * 0.85);
        int dialogHeight = (int)(displayMetrics.heightPixels * 0.85);
        dialog.getWindow().setLayout(dialogWidth, dialogHeight);

        dialog.show();
    }

    public void setTrainDialogListeners(final Dialog dialog){
        Button acceptButton = (Button) dialog.findViewById(R.id.trainDialogAccept) ;
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Accept train cards!");
                dialogPresenter.clickTrainAccept();
                dialog.dismiss();
            }
        });

        Button rejectButton = (Button) dialog.findViewById(R.id.trainDialogReject);
        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Reject train cards!");
                //do nothing
                dialog.dismiss();
            }
        });
    }

    //////////////////////////Draw train confirm Dialog//////////////////////////////////////
    public void showDrawDestDialog(){
        final Dialog dialog = new Dialog(mapActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.draw_dest_dialog); //TODO: change to new layout
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        setDrawDestDialogListeners(dialog);

        //make dialog only cover 85% of screen
        DisplayMetrics displayMetrics = mapActivity.getResources().getDisplayMetrics();
        int dialogWidth = (int)(displayMetrics.widthPixels * 0.85);
        int dialogHeight = (int)(displayMetrics.heightPixels * 0.85);
        dialog.getWindow().setLayout(dialogWidth, dialogHeight);

        dialog.show();
    }

    public void setDrawDestDialogListeners(final Dialog dialog){
        Button acceptButton = (Button) dialog.findViewById(R.id.trainDialogAccept) ;
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Accept draw dest cards!");
                dialogPresenter.clickDrawDestAccept();
                dialog.dismiss();
            }
        });

        Button rejectButton = (Button) dialog.findViewById(R.id.trainDialogReject);
        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Reject draw dest cards!");
                //do nothing
                dialog.dismiss();
            }
        });
    }
}
