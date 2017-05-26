package pl.edu.agh.student.mprezes.lvoteandroid.client.decoder.error;

import android.accounts.AuthenticatorException;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * @author Krystian Majewski
 * @since 25.05.2017
 */

public class AuthenticationErrorDecoder implements ErrorDecoder {

    private final int HTTP_ERROR_BAD_CREDENTIALS = 401;

    @Override
    public Exception decode(String methodKey, Response response) {
        if (401 == response.status()) {
            return new AuthenticatorException(response.reason());
        }
        return new RuntimeException("Could not authenticate. Response: " + response);
    }
}
