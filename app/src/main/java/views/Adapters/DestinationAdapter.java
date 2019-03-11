package views.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Models.PlayerColorEnum;
import Phase2Models.DestinationCard;
import views.R;

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder>{ //
    private OnItemClickListener listener;
    private List<DestinationCard> cardList = new ArrayList<>();
    private Map<PlayerColorEnum, Color> colorMap = new HashMap<>();

    public interface OnItemClickListener{
        void onItemClick(View gameView, String name);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public DestinationAdapter(Set<DestinationCard> inputList){
        for(DestinationCard card : inputList){
            cardList.add(card);
        }
    }

    @Override
    public DestinationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context c = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(c);
        View gameView = inflater.inflate(R.layout.destination_card_recycler_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(gameView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DestinationAdapter.ViewHolder viewHolder, int position) {
        DestinationCard card = cardList.get(position);



        viewHolder.firstCity.setText(card.getFirstCityName());
        viewHolder.secondCity.setText(card.getSecondCityName());
    }

    @Override
    public int getItemCount(){
        return cardList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView firstCity;
        public TextView secondCity;


        public ViewHolder(final View gameView) {
            super(gameView);

            firstCity =  gameView.findViewById(R.id.first_city);
            secondCity =  gameView.findViewById(R.id.second_city);

        }
    }
}
