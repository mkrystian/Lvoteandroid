package pl.edu.agh.student.mprezes.lvoteandroid.services.dto;

/**
 * Created by majew on 25.05.2017.
 */

public class AuthenticationCredentialsDTO {

    String username;
    String password;
    boolean rememberMe;

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

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
