package pl.edu.agh.student.mprezes.lvoteandroid.service.authentication;


import pl.edu.agh.student.mprezes.lvoteandroid.model.authentication.AuthenticationResult;

/**
 * @author Krystian Majewski
 * @since 26.05.2017
 */

public interface AuthenticationService {

    AuthenticationResult authenticateUser(String username, String password);
}
