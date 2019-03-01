package Views;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import Models.ActiveGame;
import Models.CityLoc;
import Models.Player;
import Phase2Models.City;
import Phase2Models.Store;
import Presenters.MapPresenter;
import Views.ActionBar;
import Views.IMap;
import Views.MapLogic;
import Views.MesssageSender;

public class MapActivity extends AppCompatActivity implements ActionBar, IMap, MesssageSender {
    MapLogic mapLogic;
    MapPresenter mapPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        FrameLayout layout = (FrameLayout) findViewById(R.id.frameLayout);
        Drawable d = getResources().getDrawable(R.drawable.game_map);
        d.setAlpha(50);
        layout.setBackgroundDrawable(d);
        construct();


    }

    private void construct(){
        mapLogic = new MapLogic(this);
        mapPresenter = new MapPresenter(this);
//        mapLogic.
        this.setContentView(mapLogic);
    }

//    public void updateMap(Map map){
//
//    }

//    public void updateChat(ChatQueue queue){

//    }
    public void updatePlayerInfo(Player player){

    }
    public void updateGameInfo(ActiveGame game){

    }
    public void updateStore(Store store){

    }
    public void updateTurnView(int playerIndex){

    }



    @Override
    public void drawStore(int i){

    }

    @Override
    public void drawTrainCarCard(){

    }

    @Override
    public void drawDestinationCard(){

    }

    @Override
    public void sendChat(String message){

    }

    @Override
    public void mapClick(float x,float y){

    }


}