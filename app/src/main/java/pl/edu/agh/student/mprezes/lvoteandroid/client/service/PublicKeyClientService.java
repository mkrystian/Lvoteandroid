package pl.edu.agh.student.mprezes.lvoteandroid.client.service;

import feign.Headers;
import feign.RequestLine;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.RSAKeyParametersDTO;

/**
 * @author Krystian Majewski
 * @since 25.05.2017
 */
public interface PublicKeyClientService extends ClientService {

    @RequestLine("GET /key/public")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    RSAKeyParametersDTO getPublicKey();
}
