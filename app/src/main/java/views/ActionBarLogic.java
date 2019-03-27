package views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

import Models.APlayer;
import Models.Player;
import Models.PlayerColorEnum;
import Phase2Models.Store;
import Phase2Models.TrainCardColor;
import Presenters.GamePresenters.*;
import views.ViewInterfaces.ActionBar;
import views.activities.MapActivity;

public class ActionBarLogic {
    private AppCompatActivity mapActivity;
    private MapPresenter mapPresenter; //TODO: should become ActionBarPresenter soon
    private ActionBar actionBarPresenter;

    public ActionBarLogic(MapActivity mapActivity){
        this.mapActivity = mapActivity;
        //this.mapPresenter = mapPresenter;
        this.actionBarPresenter = new ActionBarPresenter((MapActivity) mapActivity, this);
    }

    public void updateStore(Store store) {
        ArrayList<View> views = new ArrayList<>();
        views.add(mapActivity.findViewById(R.id.store0));
        views.add(mapActivity.findViewById(R.id.store1));
        views.add(mapActivity.findViewById(R.id.store2));
        views.add(mapActivity.findViewById(R.id.store3));
        views.add(mapActivity.findViewById(R.id.store4));

        TrainCardColor[] storeList = store.getStore();
        for(int i = 0; i < storeList.length; i++){
            views.get(i).setBackgroundColor(getTrainColor(storeList[i]));
        }
    }

    private int getTrainColor(TrainCardColor cardColor){
        int retColor = 0;
        switch (cardColor){
            case BLUE:
                retColor = mapActivity.getResources().getColor(R.color.blue);
                break;
            case RED:
                retColor = mapActivity.getResources().getColor(R.color.red);
                break;
            case BLACK:
                retColor = mapActivity.getResources().getColor(R.color.black);
                break;
            case GREEN:
                retColor = mapActivity.getResources().getColor(R.color.green);
                break;
            case WHITE:
                retColor = mapActivity.getResources().getColor(R.color.white);
                break;
            case ORANGE:
                retColor = mapActivity.getResources().getColor(R.color.orange);
                break;
            case PURPLE:
                retColor = mapActivity.getResources().getColor(R.color.purple);
                break;
            case YELLOW:
                retColor = mapActivity.getResources().getColor(R.color.yellow);
                break;
            case RAINBOW:
                retColor = mapActivity.getResources().getColor(R.color.rainbow);
                break; //TODO: make rainbow drawable
        }
        return retColor;
    }


    private int getPlayerColor(PlayerColorEnum playerColor){
        int retColor = 0;
        switch (playerColor) {
            case RED:
                retColor = mapActivity.getResources().getColor(R.color.p0Color);
                break;
            case BLUE:
                retColor = mapActivity.getResources().getColor(R.color.p1Color);
                break;
            case GREEN:
                retColor = mapActivity.getResources().getColor(R.color.p2Color);
                break;
            case YELLOW:
                retColor = mapActivity.getResources().getColor(R.color.p3Color);
                break;
            case BLACK:
                retColor = mapActivity.getResources().getColor(R.color.p4Color);
                break;
        }
        return retColor;
    }


    public void updateTurnView(int playerIndex, TreeSet<APlayer> playerList) {
        //grey out i - 1 and color i
        ArrayList<View> views = new ArrayList<>();
        views.add(mapActivity.findViewById(R.id.p0Turn));
        views.add(mapActivity.findViewById(R.id.p1Turn));
        views.add(mapActivity.findViewById(R.id.p2Turn));
        views.add(mapActivity.findViewById(R.id.p3Turn));
        views.add(mapActivity.findViewById(R.id.p4Turn));

        int i = 0;
        for(APlayer player : playerList){
            //set active player view color
            if (i == playerIndex) views.get(i).getBackground().setTint(getPlayerColor(player.getColor()));
                //set previous all other players to grey
            else views.get(i).getBackground().setTint(mapActivity.getResources().getColor(R.color.grey));
            i += 1;
        }
    }

    public void updateDestDeck(int deckSize){
        TextView tv = (TextView) mapActivity.findViewById(R.id.destDeckNum);
        tv.setText(String.valueOf(deckSize));
    }

    public void updateTrainDeck(int deckSize){
        TextView tv = (TextView) mapActivity.findViewById(R.id.trainDeckNum);
        tv.setText(String.valueOf(deckSize));
    }

//    @Override
//    public void drawStore(int i) {
//        //replace card at i with new dest card
//        actionBarPresenter.drawStore(i);
//    }
//
//    @Override
//    public void drawTrainCarCard() {
//        actionBarPresenter.drawTrainCarCard();
//    }
//
//    public void drawDestinationCard() {
//        actionBarPresenter.drawDestinationCard();
//    }

}
