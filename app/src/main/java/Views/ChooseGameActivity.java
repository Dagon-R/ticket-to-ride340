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
import android.util.Log;
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
import Models.PendingGame;
import Services.CreateGameService;
import Services.JoinGameService;
import Phase2Services.StartGameService;

public class ChooseGameActivity extends AppCompatActivity implements Observer {
    static String TAG = "Choose Game Activity";
    RecyclerView gamesRecyclerView;
    EditText gameNameTextfield;
    Button createGameButton;

    MainModel mainModel;

    View.OnClickListener createGameClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            createGame(v);
        }
    };

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_game_activity);
        gameNameTextfield = (EditText) findViewById(R.id.gameNameTextfield);
        createGameButton = (Button) findViewById(R.id.createGameButton);
        createGameButton.setOnClickListener(createGameClick);

        gamesRecyclerView = (RecyclerView) findViewById(R.id.gamesRecyclerView);
        mainModel = MainModel.get();
        gamesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ChooseGameAdapter adapter = new ChooseGameAdapter(mainModel.getGameList());
        gamesRecyclerView.setAdapter(adapter);

        mainModel.addObserver(this);
        adapter.setOnItemClickListener(new ChooseGameAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View gameView, String name) {
//                System.out.println("Inside of click for the Join Game");
                MainModel mainModel = MainModel.get();
                PendingGame game = mainModel.getGameList().getServerPendingGames().get(name);
                if(game.getPlayers().size() > 4){
                    Toast.makeText(gameView.getContext(),
                            "Game is full!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    JoinGameService service = new JoinGameService();
                    service.connectToProxy(name);
                }
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        mainModel.addObserver(this);
    }

    protected void createGame(View v){
        CreateGameService service = new CreateGameService();
        service.connectToProxy(mainModel.getUser(), gameNameTextfield.getText().toString());
//        Intent i = new Intent(this, LobbyActivity.class);
//        startActivity(i);
    }

    public void update(Observable object, Object type) {
        if(mainModel.getGame() != null){
            Intent i = new Intent(this, LobbyActivity.class);
            this.startActivity(i);
        }
        else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gamesRecyclerView.swapAdapter(new ChooseGameAdapter(mainModel.getGameList()), false);
                }
            });
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
