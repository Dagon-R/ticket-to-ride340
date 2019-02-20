package Models;

import java.util.ArrayList;

public class UserList{
    private ArrayList<User> list;

    public UserList(ArrayList<User> list) {
        this.list = new ArrayList<>();
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
}