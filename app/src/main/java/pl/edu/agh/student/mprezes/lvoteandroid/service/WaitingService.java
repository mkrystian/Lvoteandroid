package pl.edu.agh.student.mprezes.lvoteandroid.service;

import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.WaitingVote;

/**
 * @author Krystian Majewski
 * @since 16.06.2017
 */

public interface WaitingService {

    boolean addVote(WaitingVote waitingVote);
}
