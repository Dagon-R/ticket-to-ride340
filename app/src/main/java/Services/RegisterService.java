package Services;

import Communication.ServerProxy;
import Models.Player;
import Models.PlayerColorEnum;
import Models.User;

public class RegisterService implements Service {
    private ServerProxy sp;

    public RegisterService(){
        sp = ServerProxy.get();
    }

    @Override
    public Object doService(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];
        PlayerColorEnum color = (int) obj[2];


        //create user model
        User user = new User(username, password, color, id);

        //send registercommand
        Player player = sp.Register(username, password);

        //on recieve command return id?? to presenter
        //set logged in flag?

        Player player = new Player(username, color, id);
        //add player to list of all players

        return true;
    }
}