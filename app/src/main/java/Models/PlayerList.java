package Models;
import java.util.ArrayList;


public class PlayerList{
    private ArrayList<Player> list;
    private static final PlayerList ourInstance = new PlayerList();

    public static PlayerList get() {
        return ourInstance;
    }

    private PlayerList() {
        list = new ArrayList<>();
    }

    public void addPlayer(Player newPlayer){
        list.add(newPlayer);
    }

    public boolean findUser(String username, String password){
        for(Player player : list){
            if(player.getName().equals(username)){
                return true;
            }
        }
        return false;
    }

}