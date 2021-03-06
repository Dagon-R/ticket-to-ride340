package Phase2Models;

import java.text.SimpleDateFormat;
import java.util.Date;

import Models.Player;
import Models.PlayerColorEnum;

public class ChatMessage {
    private String playerName;
    private PlayerColorEnum color;
    private String message;
    private int timestamp;
    public ChatMessage(Player player, String message, int timestamp)
    {
        playerName = player.getName();
        color = player.getColor();
        this.message = message;
        this.timestamp = timestamp;
    }
    ChatMessage(String playerName, PlayerColorEnum color, String message, int timestamp)
    {
        this.playerName = playerName;
        this.color = color;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public PlayerColorEnum getColor() {
        return color;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getDate()
    {
        Date time = new Date(timestamp);
        return SimpleDateFormat.getTimeInstance().format(time);
    }
}
