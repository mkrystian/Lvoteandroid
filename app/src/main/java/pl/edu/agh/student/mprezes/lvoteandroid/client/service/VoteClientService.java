package pl.edu.agh.student.mprezes.lvoteandroid.client.service;

import java.util.Map;

import feign.HeaderMap;
import feign.RequestLine;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.BlindedVote;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.SignedVote;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.UnblindedVote;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Vote;

/**
 * @author Krystian Majewski
 * @since 11.06.2017
 */

public interface VoteClientService extends ClientService {

    @RequestLine("POST /votes")
    void sendVote(Vote vote, @HeaderMap Map<String, ?> headerMap);

    @RequestLine("PUT /vote/sign")
    SignedVote signVote(BlindedVote blindedVote, @HeaderMap Map<String, ?> headerMap);

    @RequestLine("POST /vote/unblinded")
    boolean sendUnblindedVote(UnblindedVote unblindedVote, @HeaderMap Map<String, ?> headerMap);
}
