package Phase2Services;

import Communication.ServerProxy;
import Models.MainModel;
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
        ChatMessage message = (String) obj[0];

        //sp.chat(model.getUser().getName(), message);
    }

    @Override
    //params: ChatMessage message, String ipAddress
    public void doService(Object... obj) {
        //ChatMessage message = (ChatMessage) obj[0];

        //append message to messageList
    }
}
