package pl.edu.agh.student.mprezes.lvoteandroid.services;

import android.accounts.AuthenticatorException;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Created by majew on 25.05.2017.
 */

class AuthenticationErrorDecoder implements ErrorDecoder {

    private final int HTTP_ERROR_BAD_CREDENTIALS = 401;

    @Override
    public Exception decode(String methodKey, Response response) {
        if (401 == response.status()) {
            return new AuthenticatorException("Incorrect username or password");
        }
        return new RuntimeException("Could not authenticate. Response: " + response);
    }
}
