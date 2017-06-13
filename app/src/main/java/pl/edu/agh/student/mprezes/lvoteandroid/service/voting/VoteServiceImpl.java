package pl.edu.agh.student.mprezes.lvoteandroid.service.voting;

import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.VotingAnswer;
import pl.edu.agh.student.mprezes.lvoteandroid.service.AbstractService;

/**
 * @author Krystian Majewski
 * @since 13.06.2017
 */

public class VoteServiceImpl extends AbstractService implements VoteService {
    @Override
    public boolean vote(Voting voting, VotingAnswer votingAnswer) {
        return false;
    }
}
