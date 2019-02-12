package Models;

public class UnobservedUser {
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public String getAuthToken() {
        return authToken;
    }

    //The unique name of this user
    private String name;
    //The password for this user
    private String password;
    //Says whether the current instance of the Client is logged in
    private Boolean loggedIn;
    //The auth token for server verification (if necessary)
    private String authToken;
    public UnobservedUser(User user)
    {
        this.name = user.getName();
        this.password = user.getPassword();
        this.loggedIn = user.getLoggedIn();
        this.authToken = user.getAuthToken();
    }

}
