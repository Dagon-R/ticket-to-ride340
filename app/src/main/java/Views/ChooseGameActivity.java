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
import android.widget.Toast;

public class ChooseGameActivity extends AppCompatActivity{
    //RecyclerView gamesRecyclerView;
    EditText gameNameTextfield;
    Button createGameButton;
    Button registerButton;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_game_activity);
        gameNameTextfield = (EditText) findViewById(R.id.gameNameTextfield);
        createGameButton = (EditText) findViewById(R.id.createGameButton);
        //gamesRecyclerView = (Button) findViewById(R.id.gamesRecyclerView);

    }

    protected void createGame(View v){
        Intent i = new Intent(this, LobbyActivity.class);
        startActivity(i);
    }


}
