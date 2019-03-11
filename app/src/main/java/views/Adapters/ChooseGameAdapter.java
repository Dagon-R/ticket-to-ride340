package views.Adapters;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Vector;

import Models.ClientGameList;
import Models.PendingGame;
import views.R;

public class ChooseGameAdapter extends RecyclerView.Adapter<ChooseGameAdapter.ViewHolder>{ //
    Vector<PendingGame> gameList = new Vector<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(View gameView, String name);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public ChooseGameAdapter(ClientGameList inputList){
        HashMap<String, PendingGame> pendingList = inputList.getServerPendingGames();
        for(PendingGame game : pendingList.values()){
            gameList.add(game);
        }
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
        PendingGame game = gameList.elementAt(position);

        viewHolder.gameName.setText(game.getName());
        viewHolder.gamePlayers.setText(Integer.toString(game.getPlayers().size()) + "/5");
        viewHolder.joinGameButton.setText("Join");

    }

    @Override
    public int getItemCount(){

        return gameList.size();
    }

    public void setGames(ClientGameList inputList){

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView gameName;
        public TextView gamePlayers;
        public Button joinGameButton;


        public ViewHolder(final View gameView) {
            super(gameView);

            gameName = (TextView) gameView.findViewById(R.id.gameName);
            gamePlayers = (TextView) gameView.findViewById(R.id.gamePlayers);
            joinGameButton = (Button) gameView.findViewById(R.id.joinGameButton);
            joinGameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(gameView, gameName.getText().toString());
                    }
                }
            });
        }
    }
}
