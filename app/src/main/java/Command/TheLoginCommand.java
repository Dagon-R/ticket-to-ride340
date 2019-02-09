package Command;

import Services.LoginService;

public class TheLoginCommand implements Command {
    private String username;
    private String password;
    private String ipAddress;

    @Override
    public Object execute() {
        LoginService newService = new LoginService();
        return newService.doService(username, password, ipAddress);
    }

    public TheLoginCommand(String username, String password, String ipAddress) {
        this.username = username;
        this.password = password;
        this.ipAddress = ipAddress;
    }
}