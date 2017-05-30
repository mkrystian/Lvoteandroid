package pl.edu.agh.student.mprezes.lvoteandroid.model.authentication;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

import pl.edu.agh.student.mprezes.lvoteandroid.model.ErrorMessage;

/**
 * @author Krystian Majewski
 * @since 26.05.2017
 */

public class AuthenticationResult implements Serializable {

    private static final long serialVersionUID = -210172248770708175L;

    private boolean authenticationCorrect;
    private ErrorMessage errorMessage;

    public boolean isAuthenticationCorrect() {
        return authenticationCorrect;
    }

    public void setAuthenticationCorrect(boolean authenticationCorrect) {
        this.authenticationCorrect = authenticationCorrect;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AuthenticationResult that = (AuthenticationResult) o;

        return new EqualsBuilder()
                .append(authenticationCorrect, that.authenticationCorrect)
                .append(errorMessage, that.errorMessage)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(authenticationCorrect)
                .append(errorMessage)
                .toHashCode();
    }
}
