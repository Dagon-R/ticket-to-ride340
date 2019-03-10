package Phase2Services;

import android.util.Log;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import Communication.ServerProxy;
import Models.ActiveGame;
import Models.MainModel;
import Models.PendingGame;
import Phase2Models.DestinationCard;
import Phase2Models.Store;
import Phase2Models.TrainCardColor;
import Services.Service;

public class StartGameService implements Service {
    private static String TAG="StartGameService";
    private MainModel model;

    public StartGameService(){
        model = MainModel.get();
    }

    @Override
    //params: String gameID
    public void connectToProxy(Object... obj) {
        String gameID = (String) obj[0];
        ServerProxy.get().startGame(gameID);
    }

    @Override
    //String gameID, String ipAddress, Store store, Map<ipAddress,DestinationCard[]> destcards, Map<ipAddress,TrainCard[]> trains
    public void doService(Object... obj) {

        //Check params
        if(obj.length != 5){
            model.setErrorMessage("Error Starting Game");
            System.out.println("ERROR: " + obj.length + " instead of 5 params on frontend Start Game service");
        }
        String gameID = (String) obj[0];

        String ipAddress = (String) obj[1];
        Store store = (Store) obj[2]; //game store
        HashMap<String, DestinationCard[]> destCards = (HashMap<String, DestinationCard[]>) obj[3];
        HashMap<String, Integer[]> drawnTrains = (HashMap<String, Integer[]>) obj[4];
        HashMap<String, EnumMap<TrainCardColor,Integer>> colorMap = initEnumMap(drawnTrains);

        serve(gameID,store,ipAddress,destCards,colorMap);


    }

    private void serve(String gameID, Store store, String ipAddress, HashMap<String,DestinationCard[]> destCards,HashMap<String,EnumMap<TrainCardColor,Integer>>colorMap){
        PendingGame pendingGame = model.findGame(gameID);
        //If there is a pending game, continue
        if(pendingGame == null){
            Log.d(TAG, "Not your game");
            Log.d(TAG, "Returning");
            return;
        }
        String username = model.getUsername();
        if(!destCards.containsKey(username) || !colorMap.containsKey(username)){
            Log.d(TAG, "Destination cards or Player Card Count doesn't have a slot for "+ username);
            Log.d(TAG, "Returning");
            return;
        }

        model.activateGame(gameID,store);
        model.getPlayer().setDestHand(destCards.get(model.getUser().getName()));
        //set player's train card hand
        EnumMap<TrainCardColor,Integer> userTrainCards =colorMap.get(model.getUser().getName());
        model.getPlayer().setTrainHand(userTrainCards);

            //decrement deck counts (subtract sum player train cards & dest cards)
//        }


    }


    private HashMap<String,EnumMap<TrainCardColor,Integer>> initEnumMap(HashMap<String,Integer[]> drawnTrains){
        HashMap<String, EnumMap<TrainCardColor,Integer>> colorMap = new HashMap<>();
        for (String string : drawnTrains.keySet())
        {
            colorMap.put(string,new EnumMap<TrainCardColor, Integer>(TrainCardColor.class));
            int i = 0;
            for (int count : drawnTrains.get(string))
            {
                colorMap.get(string).put(TrainCardColor.values()[i],count);
                i++;
            }
        }
        return colorMap;
    }
}
