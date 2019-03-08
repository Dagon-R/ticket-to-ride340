package Command;

import Services.ErrorService;

// Command representing that the Server ran into some kind of error
public class ErrorCommand implements Command {
    private String error; // String message of whatever error occurred
    private String ipAddress; // IP Address of player who caused the error
    private String authToken;

    @Override // Asks the Error Service to display the error
    public void execute() {
        // Creates a new error service
        ErrorService errorService = new ErrorService();
        // Causes the error service to display the error
        errorService.doService(error, ipAddress,authToken);
    }
    // Creates a new error command from a simple message
    public ErrorCommand(String message)
    {
        this.error = message; // Sets the message
        ipAddress = null; // There is no IP Address yet, the server will set that
    }
}
