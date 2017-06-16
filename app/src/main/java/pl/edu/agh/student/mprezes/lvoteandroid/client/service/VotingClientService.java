package pl.edu.agh.student.mprezes.lvoteandroid.client.service;

import java.util.List;
import java.util.Map;

import feign.HeaderMap;
import feign.RequestLine;
import pl.edu.agh.student.mprezes.lvoteandroid.client.dto.VotingDTO;

/**
 * @author Krystian Majewski
 * @since 11.06.2017
 */

public interface VotingClientService extends ClientService {

    @RequestLine("GET /votings-owned")
    List<VotingDTO> getAllUserVotings(@HeaderMap Map<String, ?> headerMap);

    @RequestLine("GET /votings-available")
    List<VotingDTO> getAvaialableVotings(@HeaderMap Map<String, ?> headerMap);

    @RequestLine("GET /results")
    List<VotingDTO> getResults(@HeaderMap Map<String, ?> headersMap);
}
