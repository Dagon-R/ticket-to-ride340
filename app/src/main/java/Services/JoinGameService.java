package Services;

public class JoinGameService implements Service {
    @Override
    public Object doService(Object... obj) {
        String GameName = (String) obj[0];
        String Player = (String) obj[1];

        //call service
        //if good, add player to game's playerlist (frontend)

        //return valid or something
        return null;
    }
}
