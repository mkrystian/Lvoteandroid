package pl.edu.agh.student.mprezes.lvoteandroid.client.service;

import java.util.Map;

import feign.HeaderMap;
import feign.RequestLine;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.AccountDTO;

/**
 * @author Krystian Majewski
 * @since 25.05.2017
 */
public interface AccountClientService extends ClientService {


    @RequestLine("GET /account")
    AccountDTO getAccount(@HeaderMap Map<String, ?> headerMap);
}
