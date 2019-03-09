package Views.Activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

//import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.view.View;


import java.util.Map;

import java.util.EnumSet;

import Models.ActiveGame;
import Models.Player;
import Phase2Models.DestinationCard;
import Phase2Models.MapModel;
import Phase2Models.Store;
import Presenters.MapPresenter;
import Views.ActionBar;
import Views.MapLogic;
import Views.R;
import Views.ViewInterfaces.IMap;
import Views.ViewInterfaces.MesssageSender;

public class MapActivity extends AppCompatActivity implements ActionBar, IMap, MesssageSender {
    static String TAG = "MapActivity";
//    MapLogic mapLogic;
    View mapLogic;
    MapPresenter mapPresenter;
    DrawerLayout drawerLayout;
    LinearLayout chatSheet;
    RecyclerView chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        construct();

    }

    private void construct() {
//        mapLogic = new MapLogic(this, this);
        mapPresenter = new MapPresenter(this);
//        drawerLayout = findViewById(R.id.activity_map_layout);
//        chatSheet = findViewById(R.id.bottom_sheet);
//        chatList = findViewById(R.id.chat_recycler_view);
        //chatSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mapLogic = findViewById(R.id.map);
//        ((MapLogic)mapLogic).setIMap(this);
//        this.setContentView(mapLogic);
    }

    public void updateMap(MapModel map){
        ((MapLogic)mapLogic).updateMap(map);
    }

//    public void updateChat(ChatQueue queue){

//    }
    public void updatePlayerInfo(Player player){

    }

    public void updateGameInfo(ActiveGame game) {

    }

    public void updateStore(Store store) {

    }

    public void updateTurnView(int playerIndex) {

    }


    @Override
    public void drawStore(int i) {

    }

    @Override
    public void drawTrainCarCard() {

    }

    @Override
    public void drawDestinationCard() {

    }

    public void drawTurnView(){

    }

    @Override
    public void sendChat(String message) {

    }

    @Override
    public void mapClick(float x, float y, PointF size) {

        mapPresenter.selectCity(x, y, size);
    }

    public void setDialogInfo(EnumSet<DestinationCard> destHand){
        showDestDialog(this, destHand);
    }

    public void showDestDialog(Context context, EnumSet<DestinationCard> destHand){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dest_card_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        setDialogText(dialog, destHand);

        setDialogListeners(dialog);

        //make dialog only cover 85% of screen
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dialogWidth = (int)(displayMetrics.widthPixels * 0.85);
        int dialogHeight = (int)(displayMetrics.heightPixels * 0.85);
        dialog.getWindow().setLayout(dialogWidth, dialogHeight);

        dialog.show();

    }

    private void setDialogListeners(final Dialog dialog){
        final Button acceptButton = (Button) dialog.findViewById(R.id.acceptDestCards);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicked button!");
                mapPresenter.clickDialogAccept();
                dialog.dismiss();
            }
        });

        CheckBox card1Check = (CheckBox) dialog.findViewById(R.id.dest1Check);
        card1Check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mapPresenter.checkDestination(isChecked, 0);
                if(mapPresenter.enoughDestsSelected()) acceptButton.setEnabled(true);
                else acceptButton.setEnabled(false);
            }
        });

        CheckBox card2Check = (CheckBox) dialog.findViewById(R.id.dest2Check);
        card2Check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mapPresenter.checkDestination(isChecked, 1);
                if(mapPresenter.enoughDestsSelected()) acceptButton.setEnabled(true);
                else acceptButton.setEnabled(false);
            }
        });

        CheckBox card3Check = (CheckBox) dialog.findViewById(R.id.dest3Check);
        card3Check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mapPresenter.checkDestination(isChecked, 2);
                if(mapPresenter.enoughDestsSelected()) acceptButton.setEnabled(true);
                else acceptButton.setEnabled(false);
            }
        });

    }

    private void setDialogText(Dialog dialog, EnumSet<DestinationCard> destHand){
        TextView dests1 = (TextView) dialog.findViewById(R.id.dests1);
        TextView dests2 = (TextView) dialog.findViewById(R.id.dests2);
        TextView dests3 = (TextView) dialog.findViewById(R.id.dests3);
        TextView val1 = (TextView) dialog.findViewById(R.id.val1);
        TextView val2 = (TextView) dialog.findViewById(R.id.val2);
        TextView val3 = (TextView) dialog.findViewById(R.id.val3);

        DestinationCard card1 = destHand.iterator().next();
        String dests1Text = card1.getFirstCityName() + " - " + card1.getSecondCityName();
        dests1.setText(dests1Text);
        val1.setText(Integer.toString(card1.getValue()));

        DestinationCard card2 =  destHand.iterator().next();
        String dests2Text = card2.getFirstCityName() + " - " + card2.getSecondCityName();
        dests2.setText(dests2Text);
        val2.setText(Integer.toString(card2.getValue()));

        DestinationCard card3 =  destHand.iterator().next();
        String dests3Text = card3.getFirstCityName() + " - " + card3.getSecondCityName();
        dests3.setText(dests3Text);
        val3.setText(Integer.toString(card3.getValue()));
    }


}
