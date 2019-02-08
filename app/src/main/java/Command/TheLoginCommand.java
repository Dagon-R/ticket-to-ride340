package Command;

import Services.LoginService;

public class TheLoginCommand implements Command {
    private String username;
    private String password;

    @Override
    public Object execute() {
        LoginService newService = new LoginService();
        return newService.doService(username, password);
    }

    public TheLoginCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }
}