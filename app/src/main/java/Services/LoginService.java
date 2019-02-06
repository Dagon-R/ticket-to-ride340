package Services;

public class LoginService implements Service {

    @Override
    public Object doService(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];

        //create user model

        //send loginCommand

        //on recieve command return playerID?? to presenter
        //set logged in flag?

        return null;
    }
}
