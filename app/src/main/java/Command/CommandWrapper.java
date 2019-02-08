package Command;

public class CommandWrapper {
    private String command;
    private String type;

    CommandWrapper(String command, String type) {
        this.command = command;
        this.type = type;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
