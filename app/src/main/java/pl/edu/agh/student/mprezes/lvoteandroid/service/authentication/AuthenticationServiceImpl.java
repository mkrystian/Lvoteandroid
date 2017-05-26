package pl.edu.agh.student.mprezes.lvoteandroid.service.authentication;

import org.apache.commons.lang3.StringUtils;

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
        authenticationServiceClient = getClientService(AuthenticationServiceClient.class);
    }

    @Override
    public AuthenticationResult authenticateUser(String username, String password) {
        AuthenticationCredentialsDTO authenticationCredentialsDTO = new AuthenticationCredentialsDTO();
        authenticationCredentialsDTO.setUsername(username);
        authenticationCredentialsDTO.setPassword(password);
        authenticationCredentialsDTO.setRememberMe(false);

        AuthenticationResponseDTO authenticationResponseDTO = authenticationServiceClient.authenticateUser(authenticationCredentialsDTO);

        AuthenticationResult result = new AuthenticationResult();

        result.setAuthenticationCorrect(StringUtils.isNotEmpty(authenticationResponseDTO.getToken()));

        return result;
    }
}
