package pl.edu.agh.student.mprezes.lvoteandroid.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import pl.edu.agh.student.mprezes.lvoteandroid.client.decoder.error.AuthenticationErrorDecoder;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.AuthenticationCredentialsDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.AuthenticationResponseDTO;
import pl.edu.agh.student.mprezes.lvoteandroid.client.service.AuthenticationServiceClient;

/**
 * @author Krystian Majewski
 * @since 25.05.2017
 */

public class ConnectionRunner implements Runnable {

    private static final String URL_API = "http://192.168.99.100:8080/api";
    @Override
    public void run() {


        AuthenticationServiceClient authenticationServiceClient = Feign.builder().encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder()).errorDecoder(new AuthenticationErrorDecoder()).target(AuthenticationServiceClient.class, URL_API);

        AuthenticationCredentialsDTO authenticationCredentialsDTO = new AuthenticationCredentialsDTO();
        authenticationCredentialsDTO.setUsername("admin");
        authenticationCredentialsDTO.setPassword("admin");
        authenticationCredentialsDTO.setRememberMe(true);

        AuthenticationResponseDTO authenticationResponseDTO = authenticationServiceClient.authenticateUser(authenticationCredentialsDTO);

        int i = 10;

    }
}
