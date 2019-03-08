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

        //PendingGame game = model.findGame(gameName);
        //int numPlayers = game.getPlayers().size();
//        String player = new Player(model.getUser().getName(), PlayerColorEnum.values()[numPlayers]);

        ServerProxy.get().joinGame(username, gameName);
    }

    @Override
    public void doService(Object... obj) {
        //Check params
        if(obj.length != 4/*5*/){
            model.setErrorMessage("Error Joining Game");
            System.out.println("ERROR: " + obj.length + " instead of 5 params on frontend joinGame service");
        }
        String player = (String) obj[0];
        String gameName = (String) obj[1];
        //String joined = (String) obj[2];
        String ipAddress = (String) obj[2];
        //ClientGameList gameList = (ClientGameList) obj[3];
        PendingGame pendingGame = (PendingGame) obj[3];

        model.addGameToGameList(pendingGame);
        //model.setGameList(gameList);
        model.addPlayerToGame(gameName, player);

        if(model.getIPAddress().equals(ipAddress)){
            model.setPendingGame(pendingGame);
        }
//        game.addPlayer(player);

    }
}
