package Command;

import Services.ErrorService;

public class ErrorCommand implements Command {
    private String error;
    private String ipAddress;
    private String authToken;

    @Override
    public void execute() {
        ErrorService errorService = new ErrorService();
        errorService.doService(error, ipAddress,authToken);
    }
    public ErrorCommand(String message)
    {
        this.error = message;
        ipAddress = null;

    }
}
