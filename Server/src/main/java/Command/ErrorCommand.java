package Command;

import java.util.Objects;

public class ErrorCommand implements Command {
    private String error;
    private String ipAddress;
    private String authToken;

    public ErrorCommand() {
    }

    public ErrorCommand(String error,String authToken) {
        this.error = error;
        this.authToken = authToken;
    }

    public ErrorCommand(String error) {
        this.error = error;
    }

    @Override
    public Object execute() {
        return null;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public void addResults(Object obj) {
    }


    public String getGameID() {
        return null;
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

    @Override
    public String toString() {
        return "ErrorCommand{" +
                "error='" + error + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
