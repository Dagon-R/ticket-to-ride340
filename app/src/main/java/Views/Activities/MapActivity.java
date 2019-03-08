package Views.Activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


import Models.ActiveGame;
import Models.Player;
import Phase2Models.MapModel;
import Phase2Models.Store;
import Presenters.MapPresenter;
import Views.MapLogic;
import Views.R;
import Views.ActionBar;
import Views.ActionBar;
import Views.MapLogic;
import Views.R;
import Views.ViewInterfaces.IMap;
import Views.ViewInterfaces.MesssageSender;

public class MapActivity extends AppCompatActivity implements ActionBar, IMap, MesssageSender {
    static String TAG = "MapActivity";
    MapLogic mapLogic;
    MapPresenter mapPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        construct();
        showDestDialog(this);

    }

    private void construct(){
        mapLogic = new MapLogic(this,this);
        mapPresenter = new MapPresenter(this);
//        mapLogic.
        this.setContentView(mapLogic);
    }

    public void updateMap(MapModel map){
        mapLogic.updateMap(map);
    }

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

    public void drawTurnView(){

    }

    @Override
    public void sendChat(String message){

    }

    @Override
    public void mapClick(float x, float y, PointF size){

        mapPresenter.selectCity(x,y,size);
    }

    public void showDestDialog(Context context){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dest_card_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        TextView dests1 = (TextView) dialog.findViewById(R.id.dests1);

        Button acceptButton = (Button) dialog.findViewById(R.id.acceptDestCards);

    }


}
