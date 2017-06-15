package pl.edu.agh.student.mprezes.lvoteandroid.model.voting;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

/**
 * @author Krystian Majewski
 * @since 12.06.2017
 */

public class Vote implements Serializable {

    private static final long serialVersionUID = -3584235055550119772L;

    private Long votingId;
    private Long answerId;
    private BigInteger randomNumber;

    public Vote() {
        randomNumber = new BigInteger(256, new Random());
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

    public BigInteger getRandomNumber() {
        return randomNumber;
    }

    public String getStringRepresentation() {
        return String.format("votingId=%s;answerId=%s;randomNumber=%s", votingId, answerId, randomNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        return new EqualsBuilder()
                .append(votingId, vote.votingId)
                .append(answerId, vote.answerId)
                .append(randomNumber, vote.randomNumber)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(votingId)
                .append(answerId)
                .append(randomNumber)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("votingId", votingId)
                .append("answerId", answerId)
                .append("randomNumber", randomNumber)
                .toString();
    }
}
