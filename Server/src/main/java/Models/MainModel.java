package Models;

import java.util.Observable;

public class MainModel extends Observable {
    private static MainModel instance;
    private GameList gameList;

    private MainModel() {
    }

    private MainModel get(){
        if(instance == null){
            instance = new MainModel();
        }
        return instance;
    }


}
