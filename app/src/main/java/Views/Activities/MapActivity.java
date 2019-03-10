package Views.Activities;

import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;

//import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;


import java.util.EnumSet;

import Models.ActiveGame;

import Models.Player;
import Phase2Models.ChatQueue;
import Phase2Models.DestinationCard;
import Phase2Models.MapModel;
import Phase2Models.Store;
import Presenters.MapPresenter;
import Views.ActionBar;
;
import Views.Adapters.ChatAdapter;
import Views.Adapters.DestinationAdapter;
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
        //showDestDialog(this);

    }

    private void construct() {
//        mapLogic = new MapLogic(this, this);

        //presenter initialization stuff
        mapPresenter = new MapPresenter(this);
        //drawer initialization stuff
        drawerLayout = findViewById(R.id.activity_map_layout);
        destinationCardDisplay = findViewById(R.id.destination_card_recycler_view);
        TextView blueCards = findViewById(R.id.blue_card_box);
        TextView redCards = findViewById(R.id.red_card_box);
        TextView yellowCards = findViewById(R.id.yellow_card_box);
        TextView greenCards = findViewById(R.id.green_card_box);
        TextView purpleCards = findViewById(R.id.purple_card_box);
        TextView orangeCards = findViewById(R.id.orange_card_box);
        TextView blackCards = findViewById(R.id.black_card_box);
        TextView whiteCards = findViewById(R.id.white_card_box);
        TextView rainbowCards = findViewById(R.id.rainbow_card_box);
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
        destinationCardDisplay.swapAdapter(new DestinationAdapter(player.getDestHand()), false);
        
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
//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dest_card_dialog);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(true);
//
//        TextView dests1 = (TextView) dialog.findViewById(R.id.dests1);
//        TextView dests2 = (TextView) dialog.findViewById(R.id.dests2);
//        TextView dests3 = (TextView) dialog.findViewById(R.id.dests3);
//        TextView val1 = (TextView) dialog.findViewById(R.id.val1);
//        TextView val2 = (TextView) dialog.findViewById(R.id.val2);
//        TextView val3 = (TextView) dialog.findViewById(R.id.val3);
//        Button acceptButton = (Button) dialog.findViewById(R.id.acceptDestCards);
//
//        DestinationCard card1 = destHand.iterator().next();
//        String dests1Text = destHand.iterator().next().getFirstCityName() + " - " + destHand.iterator().next().getSecondCityName();

        //dests1.setText();

    }


}