package Phase2Services;

import Phase2Models.ChatMessage;

public class ChatService implements Services.Service {
    //Params: ChatMessage message, String ipAddress
    @Override
    public Object doService(Object... obj)
    {
        ChatMessage message = (ChatMessage) obj[0];
        String gameID = (String) obj[1];
        String ipAddress = (String) obj[2];
        return null;

    }
}
