package Commands;

import java.util.Objects;

import Services.Service;

public class ServerLoginCommand implements Command{
    private String username;
    private String password;
    public ServerLoginCommand() {
    }

    public ServerLoginCommand(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Object execute() {
        //pass in info in constructor
        Service loginService = null;
        return loginService.doService();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerLoginCommand)) return false;
        ServerLoginCommand that = (ServerLoginCommand) o;
        return Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUsername(), getPassword());
    }


}
