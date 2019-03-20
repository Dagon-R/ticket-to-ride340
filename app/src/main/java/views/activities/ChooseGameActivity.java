package views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Observable;


import Models.ClientGameList;
import Models.MainModel;
import Presenters.ChooseGamePresenter;
import views.Adapters.ChooseGameAdapter;
import views.R;

public class ChooseGameActivity extends AppCompatActivity{
    static String TAG = "Choose Game Activity";
    RecyclerView gamesRecyclerView;
    EditText gameNameTextfield;
    Button createGameButton;
    ChooseGamePresenter chooseGamePresenter;
    MainModel mainModel;

    View.OnClickListener createGameClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            createGame(v);
        }
    };
    ChooseGameAdapter.OnItemClickListener itemClick = new ChooseGameAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View gameView, String name) {
            //                System.out.println("Inside of click for the Join Game");
            chooseGamePresenter.clickOnGame(name);

        }
    };
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_game_activity);
        setupWidgets();
        chooseGamePresenter = new ChooseGamePresenter(this);


//        mainModel.addObserver(this);

    }

    public void popToast(String message){
        Toast.makeText(this,
                message,
                Toast.LENGTH_SHORT).show();
    }

    private void setupWidgets(){
        gameNameTextfield = (EditText) findViewById(R.id.gameNameTextfield);
        createGameButton = (Button) findViewById(R.id.createGameButton);
        createGameButton.setOnClickListener(createGameClick);

        gamesRecyclerView = (RecyclerView) findViewById(R.id.gamesRecyclerView);
        mainModel = MainModel.get();
        gamesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ChooseGameAdapter adapter = new ChooseGameAdapter(mainModel.getGameList());
        gamesRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(itemClick);
    }

    public void swapAdapter(ClientGameList gameList){
        gamesRecyclerView.swapAdapter(new ChooseGameAdapter(gameList), false);
    }

    @Override
    public void onResume(){
        super.onResume();
//        mainModel.addObserver(this);
    }

    protected void createGame(View v){
        chooseGamePresenter.createGame(gameNameTextfield.getText().toString());
//        CreateGameService service = new CreateGameService();
//        service.connectToProxy(mainModel.getUser(), gameNameTextfield.getText().toString());
//        Intent i = new Intent(this, LobbyActivity.class);
//        startActivity(i);
    }

    public void switchToLobby(){
        Intent i = new Intent(this, LobbyActivity.class);
        this.startActivity(i);
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
        chooseGamePresenter.deleteObserver();
//        mainModel.deleteObserver(this);
        //log out
//        mainModel.getUser().setLoggedIn(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        chooseGamePresenter.deleteObserver();
//        mainModel.deleteObserver(this);
    }

}
