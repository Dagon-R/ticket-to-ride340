package Commands;

import Services.Service;

public class ServerLoginCommand implements Command{
    String username;
    String password;
    public ServerLoginCommand() {
    }

    @Override
    public Object execute() {
        //pass in info in constructor
        Service loginService = null;
        return loginService.doService();
    }
}
