package Presenters;

import java.util.Observable;
import java.util.Observer;

import Models.ErrorMessage;
import Models.MainModel;
import Services.LoginService;
import Services.RegisterService;
import views.activities.MainActivity;

public class LoginPresenter implements Observer {
    MainActivity mainActivity;

    public LoginPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        MainModel.get().addLoginObservers(this);
    }

    public void login(String username, String password, String server){
        LoginService service = new LoginService();
        MainModel.get().setUsername(username);
        service.connectToProxy(username, password, server);
    }

    public void register(String username, String password, String server){
        if(!username.isEmpty() && !password.isEmpty() && !server.isEmpty()) {
            RegisterService service = new RegisterService();
            MainModel.get().setUsername(username);
            service.connectToProxy(username, password, server);
            return;
        }
        MainModel.get().setErrorMessage("All fields must be filled!");


    }

    @Override
    public void update(Observable o, Object arg) {
        String name =o.getClass().getSimpleName();
        if(arg != null) name = arg.getClass().getSimpleName();
        switch(name){
            case "ErrorMessage":
                final ErrorMessage errorMessage = (ErrorMessage)o;
                runOnUI(new Runnable(){public void run() {
                    mainActivity.popToast((errorMessage).getError());
                }});
                break;
            case "ClientGameList":
                mainActivity.switchToChooseGame();
                break;

        }
    }

    private void runOnUI(Runnable run){
        mainActivity.runOnUiThread(run);
    }

    public void removeObserver(){
        MainModel.get().removeLoginObservers(this);
    }
}
