package pl.edu.agh.student.mprezes.lvoteandroid.client.service;

import feign.Headers;
import feign.RequestLine;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.AccountDTO;

/**
 * @author Krystian Majewski
 * @since 25.05.2017
 */
@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface AccountServiceClientService extends ClientService {


    @RequestLine("GET /account")
    AccountDTO getAccount();
}
