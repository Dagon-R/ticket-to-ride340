package Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.Observable;
import java.util.Observer;



import Models.MainModel;
import Services.CreateGameService;

public class ChooseGameActivity extends AppCompatActivity implements Observer {
    RecyclerView gamesRecyclerView;
    EditText gameNameTextfield;
    Button createGameButton;

    MainModel mainModel;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_game_activity);
        gameNameTextfield = (EditText) findViewById(R.id.gameNameTextfield);
        createGameButton = (Button) findViewById(R.id.createGameButton);
        gamesRecyclerView = (RecyclerView) findViewById(R.id.gamesRecyclerView);
        mainModel = MainModel.get();
        gamesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ChooseGameAdapter adapter = new ChooseGameAdapter(mainModel.getGameList());
        gamesRecyclerView.setAdapter(adapter);

        mainModel.addObserver(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        mainModel.addObserver(this);
    }

    protected void createGame(View v){
        CreateGameService service = new CreateGameService();

        Intent i = new Intent(this, LobbyActivity.class);
        startActivity(i);
    }

    public void update(Observable object, Object type) {
        if(mainModel.getGame() != null){
            Intent i = new Intent(this, LobbyActivity.class);
            startActivity(i);
        }
        else{
            gamesRecyclerView.swapAdapter(new ChooseGameAdapter(mainModel.getGameList()), false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainModel.deleteObserver(this);
        //log out
        mainModel.getUser().setLoggedIn(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainModel.deleteObserver(this);
    }
}
