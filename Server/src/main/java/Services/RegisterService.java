package Services;

public class RegisterService implements Service {
    @Override
    //Takes in String username, String password, String id, and PlayerColorEnum color
    public Object doService(Object... obj) {
        //create user model obj and set name, password, color, and id
        String username = (String) obj[0];
        String password = (String) obj[1];
        String id = (String) obj[2];
        PlayerColorEnum color = (PlayerColorEnum) obj[3];
        User newUser = new User(username, password, id, color);
        UserList users = UserList.get();
        users.add(newUser);
        return true;
        //not really any way this can fail right now.
    }
}
