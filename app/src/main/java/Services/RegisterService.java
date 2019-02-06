package Services;

public class RegisterService implements Service {

    @Override
    public Object doService(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];

        //call register service

        //if user exists, will return playerID?? (or something)
        //set some logged in flag true

        //else throw error that user does not exist

        return null;
    }
}
