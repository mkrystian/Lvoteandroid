package pl.edu.agh.student.mprezes.lvoteandroid.service;


import java.net.InetSocketAddress;
import java.net.Proxy;

import feign.Feign;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import pl.edu.agh.student.mprezes.lvoteandroid.client.service.ClientService;

/**
 * @author Krystian Majewski
 * @since 26.05.2017
 */

public abstract class AbstractService {

    //private final static String API_URL = "http://lvote.pl:8080/api";
    private final static String API_URL = "http://192.168.56.1:8080/api";

    private final Proxy proxyTest = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("203.112.211.106", 8080));

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

    protected final <T extends ClientService> T getClientService(Class<T> serviceClass, boolean proxy) {
        if (proxy) {
            okhttp3.OkHttpClient proxyConnection = new okhttp3.OkHttpClient.Builder().proxy(proxyTest).build();
            return feignBuilder()
                    .client(new OkHttpClient(proxyConnection))
                    .target(serviceClass, API_URL);
        } else {
            return feignBuilder()
                    .target(serviceClass, API_URL);
        }

    }

}
