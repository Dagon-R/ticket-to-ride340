package Phase2Services;

import java.util.Date;

import Communication.ServerProxy;
import Models.ActiveGame;

import Models.MainModel;
import Phase2Models.ChatMessage;
import Services.Service;

public class ChatService implements Service {
    private ServerProxy sp;
    private MainModel model;

    public ChatService(){
        sp = new ServerProxy();
        model = MainModel.get();
    }

    @Override
    //params: String message
    public void connectToProxy(Object... obj) {
        String messageString = (String) obj[0];
        int milliseconds = ((Long) new Date().getTime()).intValue();
        ChatMessage message = new ChatMessage(model.getPlayer(),messageString, milliseconds);
        sp.chat(message,model.getGame().getActiveGame().getId());
    }

    @Override
    //params: ChatMessage message, String ipAddress
    public void doService(Object... obj) {
        ChatMessage message = (ChatMessage) obj[0];
        String gameID = (String) obj[1];
        //String ipAddress = (String) obj[2];
        ActiveGame game = model.getGame().getActiveGame();
        if (game.getId().equals(gameID))
        {
            String className = game.getClass().getSimpleName();
            switch (className) {
                case "Active Game":
                    game.addChatMessage(message);
                    break;
                case "Pending Game":
                    System.out.println("Somehow, you tried to send a command to a pending game");
                    break;
                default:
                    System.out.println("Class name not being interpreted correctly");
                    break;
            }
        }
        else
        {
            System.out.println("Another game just got a chat message");
        }
    }
}
