package Services;
import Models.*;

public class LoginService implements Service {
    @Override
    public Object doService(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];

        //check that user exists with that username and password
        UserList users = UserList.get();
        if(users.findUser == true){
            return true;
        } else{
            return false;//MAKE THIS ERROR
        }

        return false;
    }
}
