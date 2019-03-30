package Phase2Models;

import Models.Player;
import Models.PlayerColorEnum;

public class ChatMessage {
    private String playerName;
    private PlayerColorEnum color;
    private String message;
    private int timestamp;
    ChatMessage(Player player, String message, int timestamp)
    {
        playerName = player.getName();
        color = player.getColor();
        this.message = message;
        this.timestamp = timestamp;
    }
    public ChatMessage(String name, String message){
        this.playerName = name;
        this.message = message;
    }
    ChatMessage(String playerName, PlayerColorEnum color, String message, int timestamp)
    {
        this.playerName = playerName;
        this.color = color;
        this.message = message;
        this.timestamp = timestamp;
    }

    String getMessage() {
        return message;
    }

    PlayerColorEnum getColor() {
        return color;
    }

    String getPlayerName() {
        return playerName;
    }
}
