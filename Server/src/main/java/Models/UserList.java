package Models;

import java.util.ArrayList;

public class UserList{
    private ArrayList<User> list;



    public UserList() {
        list = new ArrayList<User>();
    }

    public void addUser(User newUser){
        list.add(newUser);
    }

    public boolean findUser(String username, String password){
        for(User user : list){
            if(user.name == username && user.password == password){
                return true;
            }
        }
        return false;
    }
}