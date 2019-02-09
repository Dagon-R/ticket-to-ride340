package Services;
import java.net.InetAddress;
import java.net.UnknownHostException;

import Command.ErrorCommand;
import Models.*;

public class LoginService implements Service {
    @Override
    public Object doService(Object... obj) {
        String username = (String) obj[0];
        String password = (String) obj[1];

        //check that user exists with that username and password
//        UserList users = UserList.get();
//        if(users.findUser(username,password)){
//            return true;
//        } else{
//            try{
//                String localhost = InetAddress.getLocalHost().getHostAddress();
//                return new ErrorCommand("user does not exist", localhost);
//
//            } catch(UnknownHostException e){
//                return new ErrorCommand("user does not exist & couldn't get IPAddress", null);
//            }
//        }

        return null;
    }
}
