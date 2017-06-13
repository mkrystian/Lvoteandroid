package pl.edu.agh.student.mprezes.lvoteandroid.model.voting;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.threeten.bp.LocalDate;

import java.io.Serializable;
import java.util.Set;

import pl.edu.agh.student.mprezes.lvoteandroid.model.Account;

/**
 * @author Krystian Majewski
 * @since 10.06.2017
 */

public class Voting implements Serializable {

    private static final long serialVersionUID = -5799366682622417241L;

    private Long id;
    private String name;
    private LocalDate start;
    private LocalDate end;
    private VotingContent votingContent;
    private Set<Account> alreadyVoted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public VotingContent getVotingContent() {
        return votingContent;
    }

    public void setVotingContent(VotingContent votingContent) {
        this.votingContent = votingContent;
    }

    public Set<Account> getAlreadyVoted() {
        return alreadyVoted;
    }

    public void setAlreadyVoted(Set<Account> alreadyVoted) {
        this.alreadyVoted = alreadyVoted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("start", start)
                .append("end", end)
                .append("votingContent", votingContent)
                .append("alreadyVoted", alreadyVoted)
                .toString();
    }
}
