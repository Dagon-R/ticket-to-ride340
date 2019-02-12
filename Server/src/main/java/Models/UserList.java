package Models;

import com.google.gson.Gson;

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
            if(user.name.equals(username) && user.password.equals(password)){
                return true;
            }
        }

        return false;
    }

    public boolean findUser(String username){
        for(User user : list){
            if(user.name == username){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "list=" + list +
                '}';
    }
}