package pl.edu.agh.student.mprezes.lvoteandroid.client.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author Krystian Majewski
 * @since 08.06.2017
 */

public class UserGroupDTO implements Serializable {

    private static final long serialVersionUID = 387030124062486216L;

    private long id;
    private String name;
    private AccountDTO owner;
    private List<AccountDTO> members;
    private List<VotingDTO> votings;

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

    public List<AccountDTO> getMembers() {
        return members;
    }

    public void setMembers(List<AccountDTO> members) {
        this.members = members;
    }

    public AccountDTO getOwner() {
        return owner;
    }

    public void setOwner(AccountDTO owner) {
        this.owner = owner;
    }

    public List<VotingDTO> getVotings() {
        return votings;
    }

    public void setVotings(List<VotingDTO> votings) {
        this.votings = votings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserGroupDTO that = (UserGroupDTO) o;

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
}
