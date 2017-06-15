package pl.edu.agh.student.mprezes.lvoteandroid.client.service;

import java.util.Map;

import feign.HeaderMap;
import feign.Headers;
import feign.RequestLine;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.BlindedVote;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.SignedVote;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.UnblindedVote;

/**
 * @author Krystian Majewski
 * @since 11.06.2017
 */

public interface VoteClientService extends ClientService {

    @RequestLine("PUT /vote/sign")
    SignedVote signVote(BlindedVote blindedVote, @HeaderMap Map<String, ?> headerMap);

    @RequestLine("POST /vote/unblinded")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    boolean sendVote(UnblindedVote unblindedVote);
}
