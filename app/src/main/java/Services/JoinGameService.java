package Services;

import Communication.ServerProxy;
import Models.*;

public class JoinGameService implements Service {
    private MainModel model;

    public JoinGameService(){
        model = MainModel.get();
    }

    @Override
    public void connectToProxy(Object... obj) {
        String gameName = (String) obj[0];
        String username = (String) obj[1];



        ServerProxy.get().joinGame(username, gameName);
    }

    @Override
    public void doService(Object... obj) {
        //Check params
        if(obj.length != 4/*5*/){
            model.setErrorMessage("Error Joining Game");
            System.out.println("ERROR: " + obj.length + " instead of 5 params on frontend joinGame service");
        }
        //Grab info
        String playerName = (String) obj[0];
        String gameName = (String) obj[1];
        //String joined = (String) obj[2];
        String ipAddress = (String) obj[2];
        //ClientGameList gameList = (ClientGameList) obj[3];
        PendingGame pendingGame = (PendingGame) obj[3];

//        model.addGameToGameList(pendingGame);
        //model.setGameList(gameList);
        PendingGame game =  model.getGame().getPendingGame();
        if(game != null && game.getName().equals(gameName)){ //if in the game or joining it
            model.getGame().addToPendingGame(playerName);
        }else if(model.getIPAddress().equals(ipAddress)){
            model.setPendingGame(pendingGame);
        }else{
            model.addPlayerToGame(gameName, playerName);
        }




//        game.addPlayer(player);

    }
}
