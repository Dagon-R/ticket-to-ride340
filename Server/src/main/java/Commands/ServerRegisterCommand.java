package Commands;

import Services.Service;

public class ServerRegisterCommand implements Command{
    String username;
    String password;
    public ServerRegisterCommand() {
    }

    @Override
    public Object execute() {
        Service registerService = null;
        return registerService.doService();
    }
}
