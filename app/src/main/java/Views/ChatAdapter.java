package Views;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Models.ClientGameList;
import Models.PendingGame;
import Models.PlayerColorEnum;
import Phase2Models.ChatMessage;
import Phase2Models.ChatQueue;

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

    ChatAdapter(ChatQueue inputList){
        ArrayList<ChatMessage> pendingList = inputList.getQueue();
        messageList.addAll(pendingList);
        //colorMap.put()
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context c = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(c);
        View gameView = inflater.inflate(R.layout.chat_recycler_layout, parent, false);
        return new ViewHolder(gameView);
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder viewHolder, int position) {
        ChatMessage message = messageList.get(position);

        viewHolder.playerName.setText(message.getPlayerName());
        viewHolder.chatContent.setText(message.getMessage());
        //viewHolder.playerName.setTextColor();
    }

    @Override
    public int getItemCount(){
        return messageList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        TextView playerName;
        TextView chatContent;


        ViewHolder(final View gameView) {
            super(gameView);

            playerName = (TextView) gameView.findViewById(R.id.message_player);
            chatContent = (TextView) gameView.findViewById(R.id.message_content);
        }
    }
}

