package Views;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;


import java.util.Map;

import Models.ActiveGame;
import Models.Player;
import Phase2Models.Store;
import Presenters.MapPresenter;

public class MapActivity extends AppCompatActivity implements ActionBar,IMap,MesssageSender {
    MapLogic mapLogic;
    MapPresenter mapPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        construct();


    }

    private void construct(){
        mapLogic = new MapLogic(this);
        mapPresenter = new MapPresenter(this);
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
