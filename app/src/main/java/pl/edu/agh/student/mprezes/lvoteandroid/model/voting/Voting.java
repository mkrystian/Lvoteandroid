package pl.edu.agh.student.mprezes.lvoteandroid.model.voting;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.threeten.bp.LocalDateTime;

import java.io.Serializable;

/**
 * @author Krystian Majewski
 * @since 10.06.2017
 */

public class Voting implements Serializable {

    private static final long serialVersionUID = -5799366682622417241L;

    private Long id;
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;

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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Voting voting = (Voting) o;

        return new EqualsBuilder()
                .append(id, voting.id)
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
        return "Voting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
