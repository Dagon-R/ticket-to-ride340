package Phase2Models;

import Models.Player;
import Models.PlayerColorEnum;

public class ChatMessage {
    private String playerName;
    private PlayerColorEnum color;
    private String message;
    ChatMessage(Player player, String message)
    {
        playerName = player.getName();
        color = player.getColor();
        this.message = message;
    }
    ChatMessage(String playerName, PlayerColorEnum color, String message)
    {
        this.playerName = playerName;
        this.color = color;
        this.message = message;
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
