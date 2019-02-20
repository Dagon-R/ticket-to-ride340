package Command;

import Models.ClientGameList;
import Services.RegisterService;

public class RegisterCommand implements Command {
    private String username;
    private String password;
    private String ipAddress;
    private ClientGameList gameList;
    private String authToken;

    @Override
    public void execute() {
        RegisterService newService = new RegisterService();
        newService.doService(username, password, ipAddress, gameList, authToken);
    }

    public RegisterCommand(String username, String password, String ipAddress, String authToken) {
        this.username = username;
        this.password = password;
        this.ipAddress = ipAddress;
        this.gameList = null;
        this.authToken = authToken;

    }
}