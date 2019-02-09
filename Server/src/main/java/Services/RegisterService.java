package Services;

import Models.MainModel;
import Models.User;
import Models.UserList;

public class RegisterService implements Service {
    @Override
    //Takes in String username, String password
    public Object doService(Object... obj) {
        //create user model obj and set name, password
        String username = (String) obj[0];
        String password = (String) obj[1];
//        User newUser = new User(username, password);
        User newUser = new User(username, password);
//        MainModel.get().getUserList().findUser()
        UserList users = UserList.get();
        users.addUser(newUser);
        return true;
        //not really any way this can fail right now.
    }
}
