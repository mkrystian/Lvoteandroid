package pl.edu.agh.student.mprezes.lvoteandroid.service;

import feign.Feign;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import pl.edu.agh.student.mprezes.lvoteandroid.client.service.ClientService;

/**
 * @author Krystian Majewski
 * @since 26.05.2017
 */

public abstract class AbstractService {

    //private final static String API_URL = "http://lvote.pl:8080/api";
    private final static String API_URL = "http://192.168.1.103:8080/api";

    private Feign.Builder feignBuilder() {
        return Feign
                .builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder());
    }

    protected final <T extends ClientService> T getClientService(Class<T> serviceClass) {
        return feignBuilder()
                .target(serviceClass, API_URL);
    }

    protected final <T extends ClientService> T getClientService(Class<T> serviceClass, ErrorDecoder errorDecoder) {
        return feignBuilder()
                .errorDecoder(errorDecoder)
                .target(serviceClass, API_URL);
    }


}
