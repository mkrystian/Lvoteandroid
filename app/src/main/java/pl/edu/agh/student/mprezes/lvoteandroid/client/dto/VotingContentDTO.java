package pl.edu.agh.student.mprezes.lvoteandroid.client.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Krystian Majewski
 * @since 12.06.2017
 */

public class VotingContentDTO implements Serializable {

    private static final long serialVersionUID = -1865889095778121980L;

    private long id;
    private String question;
    private Set<VotingAnswerDTO> answers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<VotingAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<VotingAnswerDTO> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        VotingContentDTO that = (VotingContentDTO) o;

        return new EqualsBuilder()
                .append(id, that.id)
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
                .append("question", question)
                .append("answers", answers)
                .toString();
    }
}
