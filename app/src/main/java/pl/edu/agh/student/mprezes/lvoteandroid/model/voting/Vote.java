package pl.edu.agh.student.mprezes.lvoteandroid.model.voting;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author Krystian Majewski
 * @since 12.06.2017
 */

public class Vote implements Serializable {

    private static final long serialVersionUID = -3584235055550119772L;

    private Long id;
    private Long votingId;
    private Long answerId;
    private Long randomNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVotingId() {
        return votingId;
    }

    public void setVotingId(Long votingId) {
        this.votingId = votingId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(Long randomNumber) {
        this.randomNumber = randomNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        return new EqualsBuilder()
                .append(id, vote.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("votingId", votingId)
                .append("answerId", answerId)
                .append("randomNumber", randomNumber)
                .toString();
    }
}
