package Views.Activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

//import android.support.design.widget.BottomSheetBehavior;
import android.support.annotation.ColorInt;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
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
import Phase2Models.City;
import Phase2Models.MapModel;
import Phase2Models.Store;
import Presenters.MapPresenter;
import Views.ViewInterfaces.ActionBar;
import Views.MapLogic;
import Views.R;
import Views.ViewInterfaces.IMap;
import Views.ViewInterfaces.MesssageSender;

public class MapActivity extends AppCompatActivity implements ActionBar, IMap, MesssageSender {
    static String TAG = "MapActivity";
//    MapLogic mapLogic;
    MapLogic mapLogic;
    MapPresenter mapPresenter;
    DrawerLayout drawerLayout;
    LinearLayout chatSheet;
    RecyclerView chatList;
    EditText chatInput;
    Button chatButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        construct();

    }

    private void construct() {
//        mapLogic = new MapLogic(this, this);

        //presenter initialization stuff
        mapPresenter = new MapPresenter(this);
        //drawer initialization stuff
        drawerLayout = findViewById(R.id.activity_map_layout);
        //chat sheet initialization stuff
        chatSheet = findViewById(R.id.bottom_sheet);
        chatList = findViewById(R.id.chat_recycler_view);
        chatInput = findViewById(R.id.message_box);
        chatButton = findViewById(R.id.message_button);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMessage = chatInput.getText().toString();
                if(newMessage.length() == 0){
                    
                }
                else{
                    chatInput.setText("");
                    sendChat(newMessage);
                }
            }
        });

        //canvas initialization stuff
        mapLogic = findViewById(R.id.map);
        mapLogic.setIMap(this);
    }

    public void updateMap(MapModel map){
        ((MapLogic)mapLogic).updateMap(map);
    }

//    public void updateChat(ChatQueue queue){

//    }
    public void updatePlayerInfo(Player player){

    }

    public void updateGameInfo(ActiveGame game) {
        //drawer update
    }

    public void updateStore(Store store) {

    }

    public void updateTurnView(int playerIndex) {
        //grey out i - 1 and color i
        //put views into list and
    }


    @Override
    public void drawStore(int i, DestinationCard newCard) {
        //replace card at i with new dest card

    }

    @Override
    public void drawTrainCarCard() {

    }

    @Override
    public void drawDestinationCard() {

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

        //for(DestinationCard card : )

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
