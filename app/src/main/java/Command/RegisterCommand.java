package Command;

import Services.RegisterService;

public class RegisterCommand implements Command {
    private String username;
    private String password;
    private String ipAddress;

    @Override
    public void execute() {
        RegisterService newService = new RegisterService();
        newService.doService(username, password, ipAddress);
    }

    public RegisterCommand(String username, String password, String ipAddress) {
        this.username = username;
        this.password = password;
        this.ipAddress = ipAddress;

    }
}