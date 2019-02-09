package Command;

import java.util.Objects;
import Services.LoginService;
import Services.Service;

public class ServerTheLoginCommand implements Command{
    private String username;
    private String password;
    private boolean valid;
    public ServerTheLoginCommand() {
    }

    public ServerTheLoginCommand(String username, String password) {
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
    public void addResults(Object obj) {
        if(obj.getClass() != Boolean.class) return;
        Boolean val = (Boolean) obj;
        valid = val;
    }

    @Override
    public Object execute() {
        //pass in info in constructor
        Service loginService = new LoginService();
        return loginService.doService(username,password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerTheLoginCommand)) return false;
        ServerTheLoginCommand that = (ServerTheLoginCommand) o;
        return Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUsername(), getPassword());
    }


    @Override
    public String toString() {
        return "ServerTheLoginCommand\n\t{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", valid=" + valid +
                '}';
    }
}
