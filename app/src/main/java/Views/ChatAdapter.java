package Views;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Models.ClientGameList;
import Models.PendingGame;
import Models.PlayerColorEnum;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{
    private OnItemClickListener listener;
    private List<ChatMessage> messageList;
    private Map<PlayerColorEnum, Color> colorMap;

    public interface OnItemClickListener{
        void onItemClick(View gameView, String name);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    ChatAdapter(ClientGameList inputList){
        HashMap<String, PendingGame> pendingList = inputList.getServerPendingGames();
        for(PendingGame game : pendingList.values()){
            gameList.add(game);
        }
        //colorMap.put()
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context c = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(c);
        View gameView = inflater.inflate(R.layout.chat_recycler_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(gameView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder viewHolder, int position) {
        ChatMessage message = messageList.elementAt(position);

        viewHolder.playerName.setText(message.getPlayerName());
        viewHolder.chatContent.setText(message.getMessage());
        //viewHolder.playerName.setTextColor();
    }

    @Override
    public int getItemCount(){
        return messageList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView playerName;
        public TextView chatContent;


        public ViewHolder(final View gameView) {
            super(gameView);

            playerName = (TextView) gameView.findViewById(R.id.message_player);
            chatContent = (TextView) gameView.findViewById(R.id.message_content);
        }
    }
}

}
