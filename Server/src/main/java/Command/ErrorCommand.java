package Command;

import java.util.Objects;

public class ErrorCommand implements Command {
    String error;

    public ErrorCommand() {
    }

    public ErrorCommand(String error) {
        this.error = error;
    }

    @Override
    public Object execute() {
        return null;
    }

    @Override
    public void addResults(Object obj) {

    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorCommand)) return false;
        ErrorCommand that = (ErrorCommand) o;
        return Objects.equals(getError(), that.getError());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getError());
    }
}
