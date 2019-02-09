package Command;

import Services.ErrorService;

public class ErrorCommand implements Command {
    private String message;

    @Override
    public void execute() {
        ErrorService errorService = new ErrorService();
        errorService.doService(message);
    }
    public ErrorCommand(String message)
    {
        this.message = message;
    }
}
