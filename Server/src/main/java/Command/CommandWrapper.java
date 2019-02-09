package Command;

public class CommandWrapper {
    private String command;
    private String type;
    private Command com;

    public CommandWrapper(String command, String type) {
        this.command = command;
        this.type = type;
    }

    public CommandWrapper(Command command, String type){
        this.com = command;
        this.type = type;
    }

    public Command getCom() {
        return com;
    }

    public void setCom(Command com) {
        this.com = com;
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
