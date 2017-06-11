package pl.edu.agh.student.mprezes.lvoteandroid.service.voting;

import java.util.List;

import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;

/**
 * @author Krystian Majewski
 * @since 11.06.2017
 */

public interface VotingService {

    List<Voting> getAllOwnedVotings();

    List<Voting> getAllAvailableVotings();
}
