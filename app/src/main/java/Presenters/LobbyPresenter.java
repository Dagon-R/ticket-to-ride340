package Presenters;

import java.util.Observable;
import java.util.Observer;
import java.util.TreeSet;

import Models.MainModel;
import Views.Activities.LobbyActivity;

public class LobbyPresenter implements Observer {
    LobbyActivity lobbyActivity;

    public LobbyPresenter(LobbyActivity lobbyActivity) {
        this.lobbyActivity = lobbyActivity;
        retrieve();


    }

    public void retrieve(){
        TreeSet<String> players = MainModel.get().getGame().getPlayers();
        String[] playerStrings =(String[])players.toArray(new String[players.size()]);
        lobbyActivity.updatePlayers(playerStrings);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
