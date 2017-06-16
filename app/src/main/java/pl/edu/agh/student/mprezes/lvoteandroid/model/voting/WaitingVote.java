package pl.edu.agh.student.mprezes.lvoteandroid.model.voting;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author Krystian Majewski
 * @since 16.06.2017
 */

public class WaitingVote implements Serializable {
    private static final long serialVersionUID = -5587678484046745588L;

    private String votingName;
    private UnblindedVote unblindedVote;
    private VoteStatues voteStatues;

    public String getVotingName() {
        return votingName;
    }

    public void setVotingName(String votingName) {
        this.votingName = votingName;
    }

    public UnblindedVote getUnblindedVote() {
        return unblindedVote;
    }

    public void setUnblindedVote(UnblindedVote unblindedVote) {
        this.unblindedVote = unblindedVote;
    }

    public VoteStatues getVoteStatues() {
        return voteStatues;
    }

    public void setVoteStatues(VoteStatues voteStatues) {
        this.voteStatues = voteStatues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        WaitingVote that = (WaitingVote) o;

        return new EqualsBuilder()
                .append(votingName, that.votingName)
                .append(unblindedVote, that.unblindedVote)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(votingName)
                .append(unblindedVote)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("votingName", votingName)
                .append("unblindedVote", unblindedVote)
                .append("voteStatues", voteStatues)
                .toString();
    }

    public enum VoteStatues {
        NEW,
        SUBMITED,
        ERROR
    }
}
