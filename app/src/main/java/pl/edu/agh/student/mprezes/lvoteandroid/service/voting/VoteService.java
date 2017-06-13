package pl.edu.agh.student.mprezes.lvoteandroid.service.voting;

import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.VotingAnswer;

/**
 * @author Krystian Majewski
 * @since 13.06.2017
 */

public interface VoteService {

    boolean vote(Voting voting, VotingAnswer votingAnswer);

}
