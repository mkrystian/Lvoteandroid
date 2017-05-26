package pl.edu.agh.student.mprezes.lvoteandroid.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import pl.edu.agh.student.mprezes.lvoteandroid.services.dto.AuthenticationCredentialsDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.services.dto.AuthenticationResponseDTO;

/**
 * Created by majew on 25.05.2017.
 */

public class ConnectionRunner implements Runnable {

    private static final String URL_API = "http://192.168.99.100:8080/api";
    @Override
    public void run() {


        AuthenticationService authenticationService = Feign.builder().encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder()).errorDecoder(new AuthenticationErrorDecoder()).target(AuthenticationService.class, URL_API);

        AuthenticationCredentialsDTO authenticationCredentialsDTO = new AuthenticationCredentialsDTO();
        authenticationCredentialsDTO.setUsername("admin");
        authenticationCredentialsDTO.setPassword("admin");
        authenticationCredentialsDTO.setRememberMe(true);

        AuthenticationResponseDTO authenticationResponseDTO = authenticationService.authenticateUser(authenticationCredentialsDTO);

        int i = 10;

    }
}
