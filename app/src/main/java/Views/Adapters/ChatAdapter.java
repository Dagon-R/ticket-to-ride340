package Views.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Models.PlayerColorEnum;
import Phase2Models.ChatMessage;
import Phase2Models.ChatQueue;
import Views.R;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{
    private OnItemClickListener listener;
    private List<ChatMessage> messageList = new ArrayList<ChatMessage>();
    private Map<PlayerColorEnum, Color> colorMap;

    public interface OnItemClickListener{
        void onItemClick(View gameView, String name);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public ChatAdapter(ChatQueue inputList){
        ArrayList<ChatMessage> pendingList = inputList.getQueue();
        for(ChatMessage message : pendingList){
            messageList.add(message);
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
        ChatMessage message = messageList.get(position);

        PlayerColorEnum playerColorEnum = message.getColor();
        int playerColor;
        if(playerColorEnum == PlayerColorEnum.BLUE){
            playerColor = R.color.blue;
        }
        if(playerColorEnum == PlayerColorEnum.RED){
            playerColor = R.color.red;
        }
        if(playerColorEnum == PlayerColorEnum.GREEN){
            playerColor = R.color.green;
        }
        if(playerColorEnum == PlayerColorEnum.YELLOW){
            playerColor = R.color.yellow;
        }
        else{
            playerColor = R.color.black;
        }

        viewHolder.playerName.setText(message.getPlayerName());
        viewHolder.playerName.setTextColor(playerColor);
        viewHolder.chatContent.setText(message.getMessage());
        viewHolder.chatContent.setTextColor(playerColor);
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

