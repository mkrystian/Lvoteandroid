package pl.edu.agh.student.mprezes.lvoteandroid.service.authentication;

import android.accounts.AuthenticatorException;
import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.UndeclaredThrowableException;

import pl.edu.agh.student.mprezes.lvoteandroid.client.decoder.error.AuthenticationErrorDecoder;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.AuthenticationCredentialsDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.AuthenticationResponseDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.service.AuthenticationServiceClient;
import pl.edu.agh.student.mprezes.lvoteandroid.model.ErrorMessage;
import pl.edu.agh.student.mprezes.lvoteandroid.model.authentication.AuthenticationResult;
import pl.edu.agh.student.mprezes.lvoteandroid.model.context.ConnectionContext;
import pl.edu.agh.student.mprezes.lvoteandroid.service.AbstractService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.ContextProvider;
import pl.edu.agh.student.mprezes.lvoteandroid.service.account.AccountService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.account.AccountServiceImpl;

/**
 * @author Krystian Majewski
 * @since 26.05.2017
 */

public class AuthenticationServiceImpl extends AbstractService implements AuthenticationService {

    private final AuthenticationServiceClient authenticationServiceClient = getClientService(AuthenticationServiceClient.class, new AuthenticationErrorDecoder());

    @Override
    public AuthenticationResult authenticateUser(String username, String password) {
        Log.i("User authentication", "Trying to authenticate user: " + username);

        AuthenticationCredentialsDTO authenticationCredentialsDTO = new AuthenticationCredentialsDTO();
        authenticationCredentialsDTO.setUsername(username);
        authenticationCredentialsDTO.setPassword(password);
        authenticationCredentialsDTO.setRememberMe(false);

        AuthenticationResult result = new AuthenticationResult();

        try {
            AuthenticationResponseDTO authenticationResponseDTO = authenticationServiceClient.authenticateUser(authenticationCredentialsDTO);
            result.setAuthenticationCorrect(StringUtils.isNotEmpty(authenticationResponseDTO.getToken()));
            setConnectionContext(authenticationResponseDTO);
        } catch (UndeclaredThrowableException e) {
            Log.w("Authentication failed", e);

            result.setAuthenticationCorrect(false);

            if (e.getCause() instanceof AuthenticatorException) {
                result.setErrorMessage(ErrorMessage.ERROR_INCORRECT_USERNAME_OR_PASSWORD);
                return result;
            } else {
                e.printStackTrace();
            }
        }


        AccountService accountService = new AccountServiceImpl();
        accountService.setApplicationContext();

        return result;
    }

    private void setConnectionContext(AuthenticationResponseDTO authenticationResponseDTO) {
        ConnectionContext applicationContext = new ConnectionContext();
        applicationContext.setToken(authenticationResponseDTO.getToken());
        ContextProvider.setConnectionContext(applicationContext);
    }
}
