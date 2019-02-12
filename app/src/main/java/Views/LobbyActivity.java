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

import java.util.Observable;
import java.util.Observer;

import Models.MainModel;
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


    MainModel mainModel;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_activity);
        playerName0 = (TextView) findViewById(R.id.playerName0);
        playerName1 = (TextView) findViewById(R.id.playerName1);
        playerName2 = (TextView) findViewById(R.id.playerName2);
        playerName3 = (TextView) findViewById(R.id.playerName3);
        playerName4 = (TextView) findViewById(R.id.playerName4);
        startButton = (Button) findViewById(R.id.startButton);
        //backButton = (Button) findViewById(R.id.backButton);
        mainModel = MainModel.get();

    }

    public void leaveGame(View v){
        Intent i = new Intent(this, ChooseGameActivity.class);
        startActivity(i);
    }

    public void update(Observable object, Object type){
        mainModel = MainModel.get();
        String error = mainModel.getErrorMessage();
        if(error != null){
            Toast.makeText(this,
                    error,
                    Toast.LENGTH_SHORT).show();
        }
        else{
            User currentUser = mainModel.getUser();
            if(!currentUser.getLoggedIn()){
                Toast.makeText(this,
                        "Unexpected error!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
