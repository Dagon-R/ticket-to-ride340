package Command;

import Services.ErrorService;

public class ErrorCommand implements Command {
    private String message;
    private String ipAddress;

    @Override
    public void execute() {
        ErrorService errorService = new ErrorService();
        errorService.doService(message, ipAddress);
    }
    public ErrorCommand(String message)
    {
        this.message = message;
        ipAddress = null;
    }
}
