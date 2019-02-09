package Services;

import java.net.InetAddress;
import java.net.UnknownHostException;

import Command.ErrorCommand;
import Models.MainModel;
import Models.User;
import Models.UserList;

public class RegisterService implements Service {
    MainModel model;

    public RegisterService(){
        model = MainModel.get();
    }

    @Override
    //Takes in String username, String password
    public Object doService(Object... obj) {
        //create user model obj and set name, password
        String username = (String) obj[0];
        String password = (String) obj[1];

        if(model.getUserList().findUser(username, password)){ //un already taken
            try{
                String localhost = InetAddress.getLocalHost().getHostAddress();
                return new ErrorCommand("Username is already taken", localhost);
            } catch(UnknownHostException e){
                return new ErrorCommand("Username is already taken & couldn't get IPAddress", null);
            }
        } else{
            User newUser = new User(username, password);
            model.getUserList().addUser(newUser);
            return true;
        }

    }
}
