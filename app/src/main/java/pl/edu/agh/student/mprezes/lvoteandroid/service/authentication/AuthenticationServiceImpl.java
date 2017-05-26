package pl.edu.agh.student.mprezes.lvoteandroid.service.authentication;

import android.accounts.AuthenticatorException;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.UndeclaredThrowableException;

import pl.edu.agh.student.mprezes.lvoteandroid.client.decoder.error.AuthenticationErrorDecoder;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.AuthenticationCredentialsDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.AuthenticationResponseDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.service.AuthenticationServiceClient;
import pl.edu.agh.student.mprezes.lvoteandroid.model.authentication.AuthenticationResult;
import pl.edu.agh.student.mprezes.lvoteandroid.service.AbstractService;

/**
 * @author Krystian Majewski
 * @since 26.05.2017
 */

public class AuthenticationServiceImpl extends AbstractService implements AuthenticationService {

    private AuthenticationServiceClient authenticationServiceClient;

    public AuthenticationServiceImpl() {
        authenticationServiceClient = getClientService(AuthenticationServiceClient.class, new AuthenticationErrorDecoder());
    }

    @Override
    public AuthenticationResult authenticateUser(String username, String password) {
        AuthenticationCredentialsDTO authenticationCredentialsDTO = new AuthenticationCredentialsDTO();
        authenticationCredentialsDTO.setUsername(username);
        authenticationCredentialsDTO.setPassword(password);
        authenticationCredentialsDTO.setRememberMe(false);

        AuthenticationResult result = new AuthenticationResult();

        try {
            AuthenticationResponseDTO authenticationResponseDTO = authenticationServiceClient.authenticateUser(authenticationCredentialsDTO);
            result.setAuthenticationCorrect(StringUtils.isNotEmpty(authenticationResponseDTO.getToken()));
        } catch (UndeclaredThrowableException e) {
            result.setAuthenticationCorrect(false);

            if (e.getCause() instanceof AuthenticatorException) {
                result.setErrorMessage(e.getCause().getMessage());
            } else {
                e.printStackTrace();
            }
        }

        return result;
    }
}
