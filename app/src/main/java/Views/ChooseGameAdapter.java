package Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;

import Models.ClientGameList;
import Models.PendingGame;

public class ChooseGameAdapter extends RecyclerView.Adapter<ChooseGameAdapter.ViewHolder>{
    HashSet<PendingGame> gameList;

    ChooseGameAdapter(ClientGameList inputList){
        gameList = inputList.getPendingGames().values();
    }

    @Override
    public ChooseGameAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context c = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(c);
        View gameView = inflater.inflate(R.layout.choose_game_recycler_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(gameView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChooseGameAdapter.ViewHolder viewHolder, int position) {
        entry = null;//get an element of teh list

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView gameView;
        public TextView gamePlayers;
        public Button joinGameButton;

        public ViewHolder(View gameView) {
            super(gameView);

            gameView = (TextView) gameView.findViewById(R.id.gameView);
            gamePlayers = (TextView) gameView.findViewById(R.id.gamePlayers);
            joinGameButton = (Button) gameView.findViewById(R.id.joinGameButton);
        }
    }
}
