package pl.edu.agh.student.mprezes.lvoteandroid.model.context;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import feign.Headers;

/**
 * Stores Connection context info
 *
 * @author Krystian Majewski
 * @since 27.05.2017
 */
@Headers({"Accept: application/json", "Content-Type: application/json"})

public class ConnectionContext implements Serializable {

    private static final long serialVersionUID = 4325740411924876193L;

    private String token;
    private Map<String, Object> headersMap;

    public ConnectionContext() {
        createDefaultHeadersMap();
    }

    private void createDefaultHeadersMap() {
        headersMap = new HashMap<>();
        headersMap.put("Accept", "application/json");
        headersMap.put("Content-Type", "application/json");
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        headersMap.put("Authorization", "Bearer " + token);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ConnectionContext that = (ConnectionContext) o;

        return new EqualsBuilder()
                .append(token, that.token)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(token)
                .toHashCode();
    }

    public Map<String, ?> getHeadersMap() {
        return Collections.unmodifiableMap(headersMap);
    }
}
