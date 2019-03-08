package Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import Models.ActiveGame;
import Models.MainModel;
import Models.Player;
import Models.User;
import Presenters.LobbyPresenter;
import Services.LoginService;
import Services.RegisterService;
import Views.R;

public class LobbyActivity extends AppCompatActivity {
    Button startButton;
    Button backButton;
    TextView playerName0;
    TextView playerName1;
    TextView playerName2;
    TextView playerName3;
    TextView playerName4;
    LobbyPresenter lobbyPresenter;

    Player[] players;

    Vector<TextView> playerTextfields = new Vector<>();

    MainModel mainModel;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_activity);

        playerName0 = (TextView) findViewById(R.id.playerName0);
        playerName1 = (TextView) findViewById(R.id.playerName1);
        playerName2 = (TextView) findViewById(R.id.playerName2);
        playerName3 = (TextView) findViewById(R.id.playerName3);
        playerName4 = (TextView) findViewById(R.id.playerName4);
        playerTextfields.add(playerName0);
        playerTextfields.add(playerName1);
        playerTextfields.add(playerName2);
        playerTextfields.add(playerName3);
        playerTextfields.add(playerName4);
        startButton = (Button) findViewById(R.id.startButton);
        mainModel = MainModel.get();
//        mainModel.addObserver(this);
//        update(null, null);
        lobbyPresenter = new LobbyPresenter(this);
    }

    @Override
    public void onResume(){
        super.onResume();
//        mainModel.addObserver(this);
    }

    public void leaveGame(View v){

        finish();
    }

    public void updatePlayers(String[] players){
        System.out.println("PLLLLAYERS " + Arrays.toString(players));
        for(int i =0; i < 5 /*Max 5 players*/;i++){
            if(i< players.length){
                System.out.println(players[i]);
                playerTextfields.elementAt(i).setText(players[i]);
                continue;
            }
            playerTextfields.elementAt(i).setText("Waiting for player...");

        }
    }

//    public void update(Observable object, Object type){
//        mainModel = MainModel.get();
//        players = (Player[])mainModel.getGame().getPlayers().toArray();
//        if(players.length < 1){
//            finish();
//            return;
//        }
//        else{
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
////                    int i;
////                    for (i = 0; i < players.length; i++) {
////                        playerTextfields.elementAt(i).setText(((Player) players[i]).getName());
////                    }
////                    //FIXME should this be i or i+1???
////                    for (i = i; i < playerTextfields.size(); i++) {
////                        playerTextfields.elementAt(i).setText("Waiting for player...");
////                    }
//
//                    //Jacob's idea
//                    for(int i =0; i < 5 /*Max 5 players*/;i++){
//                        if(players[i] != null){
//                            playerTextfields.elementAt(i).setText(players[i].getName());
//                            continue;
//                        }
//                        playerTextfields.elementAt(i).setText("Waiting for player...");
//
//                    }
//                }
//            });
//        }
//    }

    public void startGame(View view){
        lobbyPresenter.startGame();
//        mainModel = MainModel.get();
//        if(mainModel.getGame().getPlayers().size() < 2){
//            Toast.makeText(this,
//                    "Not enough players to start!",
//                    Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Toast.makeText(this,
//                    "Game " + mainModel.getGame().getName() + " started with " + Integer.toString(mainModel.getGame().getPlayers().size()) + " players!",
//                    Toast.LENGTH_SHORT).show();
//        }
    }

    public void popToast(String message){
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT).show();
    }



    public void switchToMap(){
        Intent i = new Intent(this, MapActivity.class);
        startActivity(i);
    }



        @Override
    protected  void onDestroy() {
        super.onDestroy();
        lobbyPresenter.removeObserver();
//        mainModel.deleteObserver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lobbyPresenter.removeObserver();
    }
}
