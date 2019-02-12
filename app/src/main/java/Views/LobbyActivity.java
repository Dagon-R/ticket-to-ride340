package Views;

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

import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import Models.ActiveGame;
import Models.MainModel;
import Models.Player;
import Models.User;
import Services.LoginService;
import Services.RegisterService;

public class LobbyActivity extends AppCompatActivity implements Observer {
    Button startButton;
    Button backButton;
    TextView playerName0;
    TextView playerName1;
    TextView playerName2;
    TextView playerName3;
    TextView playerName4;

    Object[] players = new Player[5];

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
        mainModel.addObserver(this);
        update(null, null);
    }

    public void leaveGame(View v){
        finish();
    }

    public void update(Observable object, Object type){
        mainModel = MainModel.get();
        players = mainModel.getGame().getPlayers().toArray();
        if(players.length < 1){
            finish();
            return;
        }
        else{
            int i;
            for(i = 0; i < players.length; i++){
                playerTextfields.elementAt(i).setText(((Player)players[i]).getName());
            }
            for(i = i; i < playerTextfields.size(); i++){
                playerTextfields.elementAt(i).setText("Waiting for player...");
            }
        }
    }

    public void startGame(View view){
        mainModel = MainModel.get();
        if(mainModel.getGame().getPlayers().size() < 2){
            Toast.makeText(this,
                    "Not enough players to start!",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,
                    "Game " + mainModel.getGame().getName() + " started with " + Integer.toString(mainModel.getGame().getPlayers().size()) + " players!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainModel.deleteObserver(this);
    }
}
