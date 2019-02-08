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
        String name = (String) obj[0];
        String password = (String) obj[1];
        PlayerColorEnum color = PlayerColorEnum.valueOf((String)obj[2]);

        String id = "createIDHere";

        //find user model?
        //User user = users.where('name' == name);

        //send loginCommand
        //sp.sendCommand(user);

        //on recieve command return id?? to presenter
        //set logged in flag?

        Player player = new Player(name, color, id);
        //add player to list of all players

        return null;
    }
}
