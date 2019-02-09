package Services;

import Communication.ServerProxy;
import Models.Player;
import Models.PlayerColorEnum;
import Models.PlayerList;
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
        //PlayerColorEnum color = (int) obj[2] assign next unused color;

        //send loginCommand
        Player newPlayer = sp.login(username, password);

        if(newPlayer.getName() == username){
            //this client
            PlayerList playerList = PlayerList.get();
            //add player to list of all players
            playerList.addPlayer(newPlayer);
        }

        return true;
    }
}
