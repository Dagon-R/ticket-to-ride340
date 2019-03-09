package Presenters;

import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import Models.ErrorMessage;
import Models.MainModel;
import Models.PendingGame;
import Services.CreateGameService;
import Services.JoinGameService;
import Views.Activities.ChooseGameActivity;

public class ChooseGamePresenter implements Observer {
    ChooseGameActivity chooseGameActivity;

    public ChooseGamePresenter(ChooseGameActivity chooseGameActivity) {
        this.chooseGameActivity = chooseGameActivity;
        MainModel.get().addChooseGameObservers(this);

    }

    public void clickOnGame(String name){
        MainModel mainModel = MainModel.get();
        PendingGame game = mainModel.getGameList().getServerPendingGames().get(name);
        if(game.getPlayers().size() > 4){
            chooseGameActivity.popToast("Game is Full!");
            return;
        }
        else{
            JoinGameService service = new JoinGameService();
            service.connectToProxy(name,mainModel.getUser().getName());
        }
    }

    public void createGame(String gameName){
        if(gameName.equals("")) return;
        CreateGameService service = new CreateGameService();
        service.connectToProxy(MainModel.get().getUser(), gameName);
    }

    @Override
    public void update(Observable o, Object arg) {
        String name =o.getClass().getSimpleName();
        if(arg != null) name = arg.getClass().getSimpleName();
        switch(name){
            case "ErrorMessage":
                final ErrorMessage errorMessage = (ErrorMessage)o;
                runOnUI(new Runnable(){public void run() {
                        chooseGameActivity.popToast((errorMessage).getError());
                }});
                break;
            case "ClientGameList":
                    runOnUI(new Runnable() {
                        @Override
                        public void run() {
                            chooseGameActivity.swapAdapter(MainModel.get().getGameList());
                        }
                    });
                break;
            case "PendingGame":
                chooseGameActivity.switchToLobby();

        }

    }

    private void runOnUI(Runnable run){
        chooseGameActivity.runOnUiThread(run);
    }

    public void deleteObserver(){
        MainModel.get().removeChooseGameObservers(this);
    }
}
