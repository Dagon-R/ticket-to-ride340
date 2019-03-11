package views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import Models.Player;
import Models.PlayerColorEnum;

public class PlayerSummaryView extends LinearLayout {
    TextView playerPosition;
    TextView playerName;
    TextView playerPoints;
    TextView trainCards;
    TextView destCards;
    TextView playerTrains;



    public PlayerSummaryView(Context context) {
        this(context,null);
    }

    public PlayerSummaryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayerSummaryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        inflate(getContext(),R.layout.player_view,this);
        playerPosition = findViewById(R.id.player_number);
        playerName = findViewById(R.id.player_name);
        playerPoints = findViewById(R.id.player_points);
        trainCards = findViewById(R.id.player_train_cards);
        destCards = findViewById(R.id.player_destination_cards);
        playerTrains = findViewById(R.id.player_trains);
    }

    public void setInfo(Player player){
        if(playerName.getText().toString().equals("")){
            playerName.setText(player.getName());
            PlayerColorEnum playerColorEnum = player.getPlayerColor();
            int playerColor;
            int playerNumber;
            if(playerColorEnum == PlayerColorEnum.BLUE){
                playerColor = R.color.blue;
                playerNumber = 1;
            }
            if(playerColorEnum == PlayerColorEnum.RED){
                playerColor = R.color.red;
                playerNumber = 1;
            }
            if(playerColorEnum == PlayerColorEnum.GREEN){
                playerColor = R.color.green;
                playerNumber = 1;
            }
            if(playerColorEnum == PlayerColorEnum.YELLOW){
                playerColor = R.color.yellow;
                playerNumber = 1;
            }
            else{
                playerColor = R.color.black;
                playerNumber = 1;
            }
            playerName.setTextColor(playerColor);
            playerPosition.setText(String.valueOf(playerNumber) + "  ");
            playerPosition.setTextColor(playerColor);
        }

        playerPoints.setText(String.valueOf(player.getScore()));
        playerTrains.setText(String.valueOf(player.getPiecesLeft()));
        if(player.getPiecesLeft() < 3){
            playerTrains.setTextColor(Color.RED);
        } 
        trainCards.setText(String.valueOf(player.getTotalTrainCards()));
        destCards.setText(String.valueOf(player.getTotalDestinationCards()));
    }

}