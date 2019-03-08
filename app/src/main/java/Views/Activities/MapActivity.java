package Views;

import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import java.util.Map;

import Models.ActiveGame;
import Models.CityLoc;
import Models.Player;
import Phase2Models.City;
import Phase2Models.MapModel;
import Phase2Models.Store;
import Presenters.MapPresenter;
import Views.ActionBar;
import Views.IMap;
import Views.MapLogic;
import Views.MesssageSender;

public class MapActivity extends AppCompatActivity implements ActionBar, IMap, MesssageSender {
    static String TAG = "MapActivity";
    MapLogic mapLogic;
    MapPresenter mapPresenter;
    DrawerLayout drawerLayout;
    LinearLayout chatSheet;
    RecyclerView chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //FrameLayout layout = (FrameLayout) findViewById(R.id.frameLayout);
        //Drawable d = getResources().getDrawable(R.drawable.game_map);
        //d.setAlpha(50);
        //layout.setBackgroundDrawable(d);
        construct();


    }

    private void construct() {
        mapLogic = new MapLogic(this, this);
        mapPresenter = new MapPresenter(this);
        drawerLayout = findViewById(R.id.activity_map_layout);
        chatSheet = findViewById(R.id.bottom_sheet);
        chatList = findViewById(R.id.chat_recycler_view);
        //chatSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        mapLogic.
        this.setContentView(mapLogic);
    }

    public void updateMap(MapModel map) {
        mapLogic.updateMap(map);
    }

//    public void updateChat(ChatQueue queue){

    //    }
    public void updatePlayerInfo(Player player) {

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

    @Override
    public void sendChat(String message) {

    }

    @Override
    public void mapClick(float x, float y, PointF size) {

        mapPresenter.selectCity(x, y, size);
    }

}