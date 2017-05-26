package pl.edu.agh.student.mprezes.lvoteandroid.service;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import pl.edu.agh.student.mprezes.lvoteandroid.client.service.ClientService;

/**
 * @author Krystian Majewski
 * @since 26.05.2017
 */

public abstract class AbstractService {

    private final static String API_URL = "http://192.168.99.100:8080/api";

    protected final <T extends ClientService> T getClientService(Class<T> serviceClass) {
        return Feign
                .builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(serviceClass, API_URL);
    }


}
