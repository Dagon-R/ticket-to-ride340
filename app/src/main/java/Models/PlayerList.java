package Models;
import java.util.ArrayList;


public class PlayerList{
    private ArrayList<APlayer> list;
    private static final PlayerList ourInstance = new PlayerList();

    public static PlayerList get() {
        return ourInstance;
    }

    private PlayerList() {
        list = new ArrayList<>();
    }

    public void addPlayer(APlayer newPlayer){
        list.add(newPlayer);
    }

    public boolean findUser(String username, String password){
        for(APlayer player : list){
            if(player.getName().equals(username)){
                return true;
            }
        }
        return false;
    }

}