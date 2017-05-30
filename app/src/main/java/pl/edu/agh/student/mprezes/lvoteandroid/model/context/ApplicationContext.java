package pl.edu.agh.student.mprezes.lvoteandroid.model.context;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

import pl.edu.agh.student.mprezes.lvoteandroid.model.Account;

/**
 * @author Krystian Majewski
 * @since 27.05.2017
 */

public class ApplicationContext implements Serializable {

    private static final long serialVersionUID = -3104431479018094211L;

    private Account userAccount;

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ApplicationContext that = (ApplicationContext) o;

        return new EqualsBuilder()
                .append(userAccount, that.userAccount)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userAccount)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userAccount", userAccount)
                .toString();
    }
}
