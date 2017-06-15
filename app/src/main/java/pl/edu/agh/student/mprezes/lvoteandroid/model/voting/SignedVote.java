package pl.edu.agh.student.mprezes.lvoteandroid.model.voting;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

import pl.edu.agh.student.mprezes.lvoteandroid.model.utils.RSABlindSignaturesUtils.RSABlindedSignature;

/**
 * @author Krystian Majewski
 * @since 15.06.2017
 */

public class SignedVote implements Serializable {

    private static final long serialVersionUID = -3668938740569265336L;

    private RSABlindedSignature blindedSignature;

    public RSABlindedSignature getBlindedSignature() {
        return blindedSignature;
    }

    public void setBlindedSignature(RSABlindedSignature blindedSignature) {
        this.blindedSignature = blindedSignature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SignedVote that = (SignedVote) o;

        return new EqualsBuilder()
                .append(blindedSignature, that.blindedSignature)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(blindedSignature)
                .toHashCode();
    }
}
