package pl.edu.agh.student.mprezes.lvoteandroid.service;

import java.util.Collection;
import java.util.Set;

import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.WaitingVote;

/**
 * @author Krystian Majewski
 * @since 16.06.2017
 */

public interface WaitingService {

    boolean addVote(WaitingVote waitingVote);

    void updateVotes();

    void removeVotes(Collection<WaitingVote> waitingVotes);

    Set<WaitingVote> getVotes();

    boolean hasNewVotes();

    interface ValueChangeListener {
        void onValueChange();
    }
}
