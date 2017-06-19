package pl.edu.agh.student.mprezes.lvoteandroid.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * @author Krystian Majewski
 * @since 19.06.2017
 */

public class ProxyParameters implements Serializable {

    private final String address;
    private final int port;

    public ProxyParameters(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ProxyParameters that = (ProxyParameters) o;

        return new EqualsBuilder()
                .append(port, that.port)
                .append(address, that.address)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(address)
                .append(port)
                .toHashCode();
    }
}
