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


import java.util.ArrayList;
import java.util.Map;

import java.util.EnumSet;
import java.util.TreeSet;

import Models.ActiveGame;
import Models.Player;
import Models.PlayerColorEnum;
import Models.PlayerList;
import Phase2Models.DestinationCard;
import Phase2Models.City;
import Phase2Models.MapModel;
import Phase2Models.Store;
import Phase2Models.TrainCardColor;
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
    LinearLayout actionBar;


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
        //init actionbar
        actionBar = (LinearLayout) findViewById(R.id.action_bar_layout);
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
        ArrayList<View> views = new ArrayList<>();
        views.add((View) findViewById(R.id.store0));
        views.add((View) findViewById(R.id.store1));
        views.add((View) findViewById(R.id.store2));
        views.add((View) findViewById(R.id.store3));
        views.add((View) findViewById(R.id.store4));

        TrainCardColor[] storeList = store.getStore();
        for(int i = 0; i < storeList.length; i++){
            views.get(i).setBackgroundColor(getTrainColor(storeList[i]));
        }
    }

    private int getTrainColor(TrainCardColor cardColor){
        int retColor = 0;
        switch (cardColor){
            case BLUE:
                retColor = getResources().getColor(R.color.blue);
                break;
            case RED:
                retColor = getResources().getColor(R.color.red);
                break;
            case BLACK:
                retColor = getResources().getColor(R.color.black);
                break;
            case GREEN:
                retColor = getResources().getColor(R.color.green);
                break;
            case WHITE:
                retColor = getResources().getColor(R.color.white);
                break;
            case ORANGE:
                retColor = getResources().getColor(R.color.orange);
                break;
            case PURPLE:
                retColor = getResources().getColor(R.color.purple);
                break;
            case YELLOW:
                retColor = getResources().getColor(R.color.yellow);
                break;
            case RAINBOW:
                retColor = getResources().getColor(R.color.rainbow);
                break;
        }
        return retColor;
    }

    private int getPlayerColor(PlayerColorEnum playerColor){
        int retColor = 0;
        switch (playerColor) { //RBGYB
            case RED:
                retColor = getResources().getColor(R.color.p0Color);
                break;
            case BLUE:
                retColor = getResources().getColor(R.color.p1Color);
                break;
            case GREEN:
                retColor = getResources().getColor(R.color.p2Color);
                break;
            case YELLOW:
                retColor = getResources().getColor(R.color.p3Color);
                break;
            case BLACK:
                retColor = getResources().getColor(R.color.p4Color);
                break;
        }
        return retColor;
    }



    public void updateTurnView(int playerIndex, TreeSet<Player> playerList) {
        //grey out i - 1 and color i
        ArrayList<View> views = new ArrayList<>();
        views.add((View) findViewById(R.id.p0Turn));
        views.add((View) findViewById(R.id.p1Turn));
        views.add((View) findViewById(R.id.p2Turn));
        views.add((View) findViewById(R.id.p3Turn));
        views.add((View) findViewById(R.id.p4Turn));

        int i = 0;
        int lastPlayer = playerIndex - 1;
        if(lastPlayer < 0) lastPlayer = playerList.size();
        for(Player player : playerList){
            //set active player view color
            if (i == playerIndex) views.get(i).getBackground().setTint(getPlayerColor(player.getColor()));
            //set previous active player view color to grey
            if(i == lastPlayer)  views.get(i).getBackground().setTint(getResources().getColor(R.color.grey));
        }
    }


    @Override
    public void drawStore(int i) {

    }

    @Override
    public void drawTrainCarCard() {
        //decrement deck

    }

    @Override
    public void drawDestinationCard() {
        //decrement deck
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


}
