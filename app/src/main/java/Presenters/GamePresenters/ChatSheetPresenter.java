package Presenters.GamePresenters;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import Models.MainModel;
import Phase2Services.ChatService;
import Presenters.UtilPresenter;
import Services.Service;
import views.ViewInterfaces.MesssageSender;
import views.activities.MapActivity;

public class ChatSheetPresenter implements Observer, MesssageSender {
    private static String TAG = "ChatSheetPresenter";
    MapActivity activity;

    public ChatSheetPresenter(MapActivity activity) {
        this.activity = activity;
    }

    @Override
    public void update(Observable o, Object arg) {
        String type = o.getClass().getSimpleName();
        if (arg != null) {
            type = arg.getClass().getSimpleName();
        }
        if (type.equals("String")) {
            type = arg.toString();
        }
        switch (type){
            case "ChatQueue":
                updateChat();
                break;
            default:
                Log.d(TAG, "Type " + type + " is not being checked");
        }
    }

    private void updateChat(){
        UtilPresenter.runOnUI(activity,new Runnable() {
            @Override
            public void run() {
//                mapActivity.updateChat(MainModel.get().getGame().getActiveGame().getChatQueue());
            }
        });
    }



    public void sendChat(String message) {
        if (message.length() == 0) return;

        Service chatService = new ChatService();
        chatService.connectToProxy(message, MainModel.get().getGame().getActiveGame().getName());

    }


}
