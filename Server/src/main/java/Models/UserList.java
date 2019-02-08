package Models;

public class UserList{
    private ArrayList<User> list;
    private static final UserList ourInstance = new UserList();

    public static UserList get() {
        return ourInstance;
    }

    private UserList() {
        list = new ArrayList<User>;
    }

    public void addUser(User newUser){
        list.add(newUser);
    }

    public boolean findUser(String username, String password){
        for(User user : list){
            if(user.username == username && user.password == password){
                return true;
            }
        }
        return false;
    }
}