package pl.edu.agh.student.mprezes.lvoteandroid.service;


import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import feign.Feign;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import pl.edu.agh.student.mprezes.lvoteandroid.client.service.ClientService;
import pl.edu.agh.student.mprezes.lvoteandroid.model.ProxyParameters;

/**
 * @author Krystian Majewski
 * @since 26.05.2017
 */

public abstract class AbstractService {

    //private final static String HTTPS_API_URL = "https://lvote.pl:443/api";
    //private final static String API_URL = "http://lvote.pl:80/api";
    private final static String HTTPS_API_URL = "http://192.168.56.1:8080/api";
    private final static String API_URL = "http://192.168.56.1:8081/api";

    private final List<ProxyParameters> proxyList = Arrays.asList(
            new ProxyParameters("187.16.2.160", 8080), new ProxyParameters("183.222.102.94", 8080));

    private Feign.Builder feignBuilder() {

        return Feign
                .builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder());
    }

    protected final <T extends ClientService> T getClientService(Class<T> serviceClass) {
        return feignBuilder()
                .target(serviceClass, HTTPS_API_URL);
    }

    protected final <T extends ClientService> T getClientService(Class<T> serviceClass, ErrorDecoder errorDecoder) {
        return feignBuilder()
                .errorDecoder(errorDecoder)
                .target(serviceClass, HTTPS_API_URL);
    }

    protected final <T extends ClientService> List<T> getClientService(Class<T> serviceClass, boolean proxy) {
        if (proxy) {
            List<T> result = new ArrayList<>();
            for (ProxyParameters proxyParameters : proxyList) {
                result.add(feignBuilder()
                        .client(new OkHttpClient(new okhttp3.OkHttpClient.Builder()
                                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyParameters.getAddress(), proxyParameters.getPort()))).build()))
                        .target(serviceClass, API_URL));
            }
            return result;
        } else {
            return Collections.singletonList(feignBuilder()
                    .target(serviceClass, HTTPS_API_URL));
        }

    }

}
