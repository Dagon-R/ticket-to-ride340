package Command;

import Models.ClientGameList;
import Services.LoginService;

public class TheLoginCommand implements Command {
    private String username;
    private String password;
    private String ipAddress;
    private ClientGameList gameList;

    @Override
    public void execute() {
        LoginService newService = new LoginService();
        newService.doService(username, password, ipAddress, gameList);
    }

    public TheLoginCommand(String username, String password, String ipAddress) {
        this.username = username;
        this.password = password;
        this.ipAddress = ipAddress;
        this.gameList = null;
    }
}