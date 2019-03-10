package Views.Activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

//import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Map;

import java.util.EnumSet;

import Models.ActiveGame;
import Models.Player;
import Phase2Models.ChatQueue;
import Phase2Models.ChatQueue;
import Phase2Models.DestinationCard;
import Phase2Models.MapModel;
import Phase2Models.Store;
import Presenters.MapPresenter;
//import Views.ActionBar;
;
import Views.Adapters.ChatAdapter;
import Views.Adapters.DestinationAdapter;
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
    RecyclerView destinationCardDisplay;
    TextView blueCards;
    TextView redCards;
    TextView yellowCards;
    TextView greenCards;
    TextView purpleCards;
    TextView orangeCards;
    TextView blackCards;
    TextView whiteCards;
    TextView rainbowCards;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        construct();

    }

    private void construct() {
//        mapLogic = new MapLogic(this, this);

        //presenter initialization stuff

        //drawer initialization stuff
        drawerLayout = findViewById(R.id.activity_map_layout);
        destinationCardDisplay = findViewById(R.id.destination_card_recycler_view);
        //chat sheet initialization stuff
        chatSheet = findViewById(R.id.bottom_sheet);

        chatList = findViewById(R.id.chat_recycler_view);
        chatList.setLayoutManager(new LinearLayoutManager(this));
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
        mapPresenter = new MapPresenter(this);
    }

    public void updateMap(MapModel map){
        mapLogic.updateMap(map);
    }


    public void updatePlayerInfo(Player player){
        destinationCardDisplay.swapAdapter(new DestinationAdapter(player.getDestHand()), false);
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
    public void drawStore(int i) {
        //replace card at i with new dest card
        mapPresenter.drawStore(i);

    }

    @Override
    public void drawTrainCarCard() {
        mapPresenter.drawTrainCard();
    }

    @Override
    public void drawDestinationCard() {
        mapPresenter.drawDestination();
    }


    @Override
    public void sendChat(String message) {
        mapPresenter.sendMessage(message);
    }

    public void updateChat(ChatQueue queue){
        chatList.swapAdapter(new ChatAdapter(queue), false);
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
