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
            if(user.getName().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean findUser(String username){
        for(User user : list){
            if(user.getName().equals(username)){
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