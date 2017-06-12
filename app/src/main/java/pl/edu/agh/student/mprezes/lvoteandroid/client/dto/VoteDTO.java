package pl.edu.agh.student.mprezes.lvoteandroid.client.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author Krystian Majewski
 * @since 12.06.2017
 */

public class VoteDTO implements Serializable {

    private static final long serialVersionUID = 1845753722105648421L;

    private long id;
    private int answer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        VoteDTO voteDTO = (VoteDTO) o;

        return new EqualsBuilder()
                .append(id, voteDTO.id)
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
                .append("answer", answer)
                .toString();
    }
}
