package views.activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.TreeSet;

import Models.ActiveGame;
import Models.Player;
import Models.PlayerColorEnum;
import Phase2Models.ChatQueue;
import Phase2Models.DestinationCard;
import Phase2Models.MapModel;
import Phase2Models.Store;
import Phase2Models.TrainCardColor;
import Presenters.MapPresenter;
import views.Adapters.ChatAdapter;
import views.Adapters.DestinationAdapter;
import views.MapLogic;
import views.PlayerSummaryView;
import views.R;
import views.ViewInterfaces.ActionBar;
import views.ViewInterfaces.IMap;
import views.ViewInterfaces.MesssageSender;

//import android.support.design.widget.BottomSheetBehavior;

public class MapActivity extends AppCompatActivity implements ActionBar, IMap, MesssageSender { //
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

    PlayerSummaryView[] playersArray;
    PlayerSummaryView player1;
    PlayerSummaryView player2;
    PlayerSummaryView player3;
    PlayerSummaryView player4;
    PlayerSummaryView player5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        construct();

    }



    private void construct() {
        setupActionBar();
        setupDrawers();
        setupChatSheet();
        setupMap();
        mapPresenter = new MapPresenter(this);

    }

    private void setupActionBar(){
        actionBar = findViewById(R.id.action_bar_layout);
    }

    private void setupMap(){
        mapLogic = findViewById(R.id.map);
        mapLogic.setIMap(this);
    }

    private void setupPlayerDrawer(){
        destinationCardDisplay = findViewById(R.id.destination_card_recycler_view);
        destinationCardDisplay.setAdapter(new DestinationAdapter(new HashSet<DestinationCard>()));
        destinationCardDisplay.setLayoutManager(new LinearLayoutManager(this));
        blueCards = findViewById(R.id.blue_card_box);
        redCards = findViewById(R.id.red_card_box);
        yellowCards = findViewById(R.id.yellow_card_box);
        greenCards = findViewById(R.id.green_card_box);
        purpleCards = findViewById(R.id.purple_card_box);
        orangeCards = findViewById(R.id.orange_card_box);
        blackCards = findViewById(R.id.black_card_box);
        whiteCards = findViewById(R.id.white_card_box);
        rainbowCards = findViewById(R.id.rainbow_card_box);
    }

    private void setupGameDrawer(){
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        player3 = findViewById(R.id.player3);
        player4 = findViewById(R.id.player4);
        player5 = findViewById(R.id.player5);
        playersArray = new PlayerSummaryView[]{player1, player2, player3, player4, player5};
    }

    private void setupChatSheet(){
        chatSheet = findViewById(R.id.bottom_sheet);
        chatList = findViewById(R.id.chat_recycler_view);
        chatList.setLayoutManager(new LinearLayoutManager(this));
        chatInput = findViewById(R.id.message_box);
        chatButton = findViewById(R.id.message_button);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMessage = chatInput.getText().toString();
                sendChat(newMessage);

            }
        });
    }

    private void setupDrawers(){
        drawerLayout = findViewById(R.id.activity_map_layout);
        setupPlayerDrawer();
        setupGameDrawer();
    }

    public void updateMap(MapModel map){
        mapLogic.updateMap(map);
    }

    public void popToast(String error){
        Toast.makeText(MapActivity.this,error,Toast.LENGTH_SHORT).show();
    }

//    public void updateChat(ChatQueue queue){


    public void updateDestinationCards(EnumSet<DestinationCard> destinationCards){
        destinationCardDisplay.swapAdapter(new DestinationAdapter(destinationCards), false);

    }

    public void updateTrainCards(Player player){
        blueCards.setText(String.valueOf(player.getCardColorCount(TrainCardColor.BLUE)));
        redCards.setText((String.valueOf(player.getCardColorCount(TrainCardColor.RED))));
        yellowCards.setText(String.valueOf(player.getCardColorCount(TrainCardColor.YELLOW)));
        greenCards.setText(String.valueOf(player.getCardColorCount(TrainCardColor.GREEN)));
        purpleCards.setText(String.valueOf(player.getCardColorCount(TrainCardColor.PURPLE)));
        orangeCards.setText(String.valueOf(player.getCardColorCount(TrainCardColor.ORANGE)));
        blackCards.setText(String.valueOf(player.getCardColorCount(TrainCardColor.BLACK)));
        whiteCards.setText(String.valueOf(player.getCardColorCount(TrainCardColor.WHITE)));
        rainbowCards.setText(String.valueOf(player.getCardColorCount(TrainCardColor.RAINBOW)));
    }

    public void updateGameInfo(ActiveGame game) {
        TreeSet<Player> players = game.getPlayers();
        int i = 0;
        System.out.println(game);
        for (Player currentPlayer : players){
            playersArray[i].setInfo(currentPlayer);
            i++;
        }
    }

    public void updateStore(Store store) {
        ArrayList<View> views = new ArrayList<>();
        views.add(findViewById(R.id.store0));
        views.add(findViewById(R.id.store1));
        views.add(findViewById(R.id.store2));
        views.add(findViewById(R.id.store3));
        views.add(findViewById(R.id.store4));

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
                break; //TODO: make rainbow drawable
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
        views.add(findViewById(R.id.p0Turn));
        views.add(findViewById(R.id.p1Turn));
        views.add(findViewById(R.id.p2Turn));
        views.add(findViewById(R.id.p3Turn));
        views.add(findViewById(R.id.p4Turn));

        int i = 0;
        for(Player player : playerList){
            //set active player view color
            if (i == playerIndex) views.get(i).getBackground().setTint(getPlayerColor(player.getColor()));
            //set previous all other players to grey
            else views.get(i).getBackground().setTint(getResources().getColor(R.color.grey));
            i += 1;
        }
    }

    public void updateDestDeck(int deckSize){
        TextView tv = (TextView) findViewById(R.id.destDeckNum);
        tv.setText(String.valueOf(deckSize));
    }

    public void updateTrainDeck(int deckSize){
        TextView tv = (TextView) findViewById(R.id.trainDeckNum);
        tv.setText(String.valueOf(deckSize));
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
