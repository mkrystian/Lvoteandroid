package pl.edu.agh.student.mprezes.lvoteandroid.client.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Krystian Majewski
 * @since 08.06.2017
 */

public class VotingDTO implements Serializable {

    private static final long serialVersionUID = 6608489999088581210L;

    private long id;
    private String name;
    private String startDate;
    private String endDate;
    private VotingContentDTO content;
    private Set<AccountDTO> alreadyVoteds;
    private Set<VoteDTO> votes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public VotingContentDTO getContent() {
        return content;
    }

    public void setContent(VotingContentDTO content) {
        this.content = content;
    }

    public Set<AccountDTO> getAlreadyVoteds() {
        return alreadyVoteds;
    }

    public void setAlreadyVoteds(Set<AccountDTO> alreadyVoteds) {
        this.alreadyVoteds = alreadyVoteds;
    }

    public Set<VoteDTO> getVotes() {
        return votes;
    }

    public void setVotes(Set<VoteDTO> votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        VotingDTO votingDTO = (VotingDTO) o;

        return new EqualsBuilder()
                .append(id, votingDTO.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}
