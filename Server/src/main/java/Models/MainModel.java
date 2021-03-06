package Models;
//import UserList;
import java.util.Observable;

public class MainModel extends Observable {
    private static MainModel instance;
    private GameList gameList;
    private UserList userList;

    private MainModel() {
        gameList = new GameList();
        userList = new UserList();
//        userList.addUser(new User("Host","HostPass"));
    }

    public static MainModel get(){
        if(instance == null){
            instance = new MainModel();
        }
        return instance;
    }

    public GameList getGameList() {
        return gameList;
    }

    public void setGameList(GameList gameList) {
        this.gameList = gameList;
    }

    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }
}
