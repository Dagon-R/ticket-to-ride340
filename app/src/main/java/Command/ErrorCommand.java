package Command;

import Services.ErrorService;

public class ErrorCommand implements Command {
    private String error;
    private String ipAddress;

    @Override
    public void execute() {
        ErrorService errorService = new ErrorService();
        errorService.doService(error, ipAddress);
    }
    public ErrorCommand(String message)
    {
        this.error = message;
        ipAddress = null;
    }
}
