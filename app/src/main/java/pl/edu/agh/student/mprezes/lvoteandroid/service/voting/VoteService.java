package pl.edu.agh.student.mprezes.lvoteandroid.service.voting;

import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.VotingAnswer;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.WaitingVote;

/**
 * @author Krystian Majewski
 * @since 13.06.2017
 */

public interface VoteService {

    boolean vote(Voting voting, VotingAnswer votingAnswer, boolean useProxy);

    boolean addToWaitingList(Voting voting, VotingAnswer votingAnswer);

    boolean sendWaitingVote(WaitingVote waitingVote, boolean useProxy);
}
