package Services;

public class RegisterService implements Service {
    @Override
    //Takes in String username, String password
    public Object doService(Object... obj) {
        //create user model obj and set name, password
        String username = (String) obj[0];
        String password = (String) obj[1];
        User newUser = new User(username, password);
        UserList users = UserList.get();
        users.add(newUser);
        return true;
        //not really any way this can fail right now.
    }
}
