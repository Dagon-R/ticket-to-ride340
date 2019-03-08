package Views.Activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


import java.util.EnumSet;

import Models.ActiveGame;
import Models.Player;
import Phase2Models.DestinationCard;
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
    View map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        construct();
        //showDestDialog(this);

    }

    private void construct(){
        mapLogic = new MapLogic(this,this);
        mapPresenter = new MapPresenter(this);
//        mapLogic.
        map.findViewById(R.id.map);
//        this.setContentView(mapLogic);
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

    public void setDialogInfo(EnumSet<DestinationCard> destHand){
        showDestDialog(this, destHand);
    }

    public void showDestDialog(Context context, EnumSet<DestinationCard> destHand){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dest_card_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        TextView dests1 = (TextView) dialog.findViewById(R.id.dests1);
        TextView dests2 = (TextView) dialog.findViewById(R.id.dests2);
        TextView dests3 = (TextView) dialog.findViewById(R.id.dests3);
        TextView val1 = (TextView) dialog.findViewById(R.id.val1);
        TextView val2 = (TextView) dialog.findViewById(R.id.val2);
        TextView val3 = (TextView) dialog.findViewById(R.id.val3);
        Button acceptButton = (Button) dialog.findViewById(R.id.acceptDestCards);

        DestinationCard card1 = destHand.iterator().next();
        String dests1Text = destHand.iterator().next().getFirstCityName() + " - " + destHand.iterator().next().getSecondCityName();

        //dests1.setText();

    }


}
