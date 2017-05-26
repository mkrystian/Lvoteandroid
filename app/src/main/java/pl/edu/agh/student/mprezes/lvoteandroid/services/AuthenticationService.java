package pl.edu.agh.student.mprezes.lvoteandroid.services;

import feign.Headers;
import feign.RequestLine;
import pl.edu.agh.student.mprezes.lvoteandroid.services.dto.AuthenticationCredentialsDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.services.dto.AuthenticationResponseDTO;

/**
 * Created by majew on 25.05.2017.
 */
@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface AuthenticationService {

    @RequestLine("POST /authenticate")
    AuthenticationResponseDTO authenticateUser(AuthenticationCredentialsDTO authenticationCredentialsDTO);
}
