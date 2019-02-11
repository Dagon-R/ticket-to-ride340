package Command;

import Models.ClientGameList;
import Services.RegisterService;

public class RegisterCommand implements Command {
    private String username;
    private String password;
    private String ipAddress;
    private ClientGameList gameList;

    @Override
    public void execute() {
        RegisterService newService = new RegisterService();
        newService.doService(username, password, ipAddress, gameList);
    }

    public RegisterCommand(String username, String password, String ipAddress) {
        this.username = username;
        this.password = password;
        this.ipAddress = ipAddress;
        this.gameList = null;

    }
}