package pl.edu.agh.student.mprezes.lvoteandroid.service.voting;

import pl.edu.agh.student.mprezes.lvoteandroid.client.service.VoteClientService;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Vote;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.VotingAnswer;
import pl.edu.agh.student.mprezes.lvoteandroid.service.AbstractService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.ContextProvider;

/**
 * @author Krystian Majewski
 * @since 13.06.2017
 */

public class VoteServiceImpl extends AbstractService implements VoteService {

    private final VoteClientService clientService = getClientService(VoteClientService.class);

    @Override
    public boolean vote(Voting voting, VotingAnswer votingAnswer) {
        Vote vote = new Vote();
        vote.setVotingId(voting.getId());
        vote.setAnswerId(votingAnswer.getId());

        clientService.sendVote(vote, ContextProvider.getHeadersMap());

        encryptContent();

        return true;
    }

    private void encryptContent() {

    }
}
