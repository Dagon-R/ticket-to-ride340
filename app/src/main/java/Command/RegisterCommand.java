package Command;

import Services.RegisterService;

public class RegisterCommand implements Command {
    private String username;
    private String password;

    @Override
    public Object execute() {
        RegisterService newService = new RegisterService();
        return newService.doService(username, password);
    }

    RegisterCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }
}