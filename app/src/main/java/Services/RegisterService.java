package Services;

import Communication.ServerProxy;
import Models.Player;
import Models.User;
import Models.UserList;

public class RegisterService implements Service {
    private ServerProxy sp;

    public RegisterService(){
        sp = ServerProxy.get();
    }

    @Override
    public Object doService(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];

        //create user model
        User newUser = new User(username, password);

        //add user to list of all user
        UserList users = UserList.get();
        users.addUser(newUser);

        return true;
    }
}
}
