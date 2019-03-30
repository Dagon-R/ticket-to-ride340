package Command;

// This class is used to send commands to and from the server. It holds the JSON representation
// of a command, along with a type string that specifies what type of command to unwrap to
public class CommandWrapper {
    private String command; // The JSON representation of a command
    private String type; // A string representing the subclass of command that was wrapped

    // Creates a command from the JSON representation of the command and the subclass name
    public CommandWrapper(String command, String type) {
        this.command = command;
        this.type = type;
    }

    // Returns the JSON representation of the wrapped command
    public String getCommand() {
        return command;
    }

    // Places the JSON representation of a command into the wrapper
    public void setCommand(String command) {
        this.command = command;
    }

    // Gets a string representing the subclass of command which was wrapped
    public String getType() {
        return type;
    }

    // Sets a string representing the subclass of command which was wrapped
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CommandWrapper{" +
                "command='" + command + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
