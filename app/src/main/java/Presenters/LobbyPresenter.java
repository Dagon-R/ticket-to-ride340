package Presenters;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeSet;

import Models.ErrorMessage;
import Models.MainModel;
import Phase2Services.StartGameService;
import Views.Activities.LobbyActivity;

public class LobbyPresenter implements Observer {
    static String TAG = "LobbyPresenter";
    LobbyActivity lobbyActivity;

    public LobbyPresenter(LobbyActivity lobbyActivity) {
        this.lobbyActivity = lobbyActivity;
        retrievePlayers();
        MainModel.get().addLobbyObservers(this);

    }

    public void retrievePlayers(){

        runOnUI(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> players = MainModel.get().getGame().getPendingGame().getPlayers();

                lobbyActivity.updatePlayers(players);
            }
        });

    }

    public void startGame(){

        if(MainModel.get().getGame().getPendingGame().getPlayers().size() < 2){ //TODO: uncomment
            lobbyActivity.popToast("Not enough players. You need 2-5 players to play");
            return;
        }
        StartGameService startGameService = new StartGameService();
        startGameService.connectToProxy(MainModel.get().getGame().getPendingGame().getName());
    }

    @Override
    public void update(Observable o, Object arg) {
        String name =o.getClass().getSimpleName();

        if(arg != null) name = arg.getClass().getSimpleName();
        Log.d(TAG, "update: " + name);
        switch(name){
            case "ErrorMessage":
                final ErrorMessage errorMessage = (ErrorMessage)o;
                runOnUI(new Runnable(){public void run() {
                    lobbyActivity.popToast((errorMessage).getError());
                }});
                break;

            case "PendingGame":
                retrievePlayers();
                break;
            case "ActiveGame":
                lobbyActivity.switchToMap();
                break;
            case "ClientGameList":
                retrievePlayers();
                break;
                default:
                    Log.d(TAG, "INVALID CLASS "+ name);
        }
    }
    private void runOnUI(Runnable run){
        lobbyActivity.runOnUiThread(run);
    }

    public void removeObserver(){
        MainModel.get().removeLobbyObservers(this);
    }
}
