package Services;

import Communication.ServerProxy;
import Models.Player;
import Models.PlayerColorEnum;
import Models.User;

public class LoginService implements Service {
    private ServerProxy sp;

    public LoginService(){
        sp = ServerProxy.get();
    }

    @Override
    public Object doService(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];
        PlayerColorEnum color = PlayerColorEnum.valueOf((String)obj[2]);

        //send loginCommand
        Player player = sp.login(username, password);

        //add player to list of all players

        return true;
    }
}
