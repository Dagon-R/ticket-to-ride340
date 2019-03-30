package Phase2Services;

import java.util.Date;

import Communication.ServerProxy;
import Models.ActiveGame;

import Models.MainModel;
import Models.Player;
import Phase2Models.ChatMessage;
import Services.Service;

public class ChatService implements Service {
    private MainModel model;

    public ChatService(){
        model = MainModel.get();
    }

    @Override
    //params: String message
    public void connectToProxy(Object... obj) {
        String messageString = (String) obj[0];
        int milliseconds = ((Long) new Date().getTime()).intValue();
        ChatMessage message = new ChatMessage(model.getPlayer(),messageString, milliseconds);
        ServerProxy.get().chat(message,model.getGame().getActiveGame().getName());
    }

    @Override
    //params: ChatMessage message, String ipAddress
    public void doService(Object... obj) {
        ChatMessage message = (ChatMessage) obj[0];
        String gameID = (String) obj[1];
        //String ipAddress = (String) obj[2];
        ActiveGame game = model.getGame().getActiveGame();
        if(game == null){
            System.out.println("You dont have a game. Stop trying to receive messages");
            return;
        }
        if(!game.getName().equals(gameID)){
            System.out.println("Another Active game just got a chat message");
            return;
        }
        if(message.getPlayerName().equals("Console")){
            Player p = (Player)game.getPlayers().toArray()[ game.getActivePlayerInd()];
            StringBuilder history = new StringBuilder(message.getMessage());
            history.insert(0,p.getName() +": ");
            message.setMessage(history.toString());
        }

        game.addChatMessage(message);

    }
}
