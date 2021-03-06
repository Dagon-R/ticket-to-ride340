package Services;

import Command.ErrorCommand;
import Models.*;

public class LoginService implements Service {
    MainModel model;

    public LoginService(){
        model = MainModel.get();
    }

    @Override
    public Object doService(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];
        String authToken = (String) obj[2];

        //check that user exists with that username and password
        if(model.getUserList().findUser(username, password)){
            return new GameList(MainModel.get().getGameList());
        } else{
            return new ErrorCommand("user does not exist",authToken);
        }

    }
}
