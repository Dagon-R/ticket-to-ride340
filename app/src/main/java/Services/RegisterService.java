package Services;

import Communication.ServerProxy;

public class RegisterService implements Service {
    private ServerProxy sp;

    public RegisterService(){
        sp = ServerProxy.get();
    }

    @Override
    public Object doService(Object... obj) {
        String name = (String) obj[0];
        String password = (String) obj[1];
        //PlayerColorEnum color = (int) obj[2];

        String id = "createIDHere";

        //create user model
       // User user = new User(name, password, color, id);

        //send registercommand
        //sp.sendCommand(user);

        //on recieve command return id?? to presenter
        //set logged in flag?

        //Player player = new Player(name, color, id);
        //add player to list of all players

        return null;
    }
}