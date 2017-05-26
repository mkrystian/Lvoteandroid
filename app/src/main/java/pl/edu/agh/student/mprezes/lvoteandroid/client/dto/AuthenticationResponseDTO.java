package pl.edu.agh.student.mprezes.lvoteandroid.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Krystian Majewski
 * @since 25.05.2017
 */

public class AuthenticationResponseDTO {

    @JsonProperty("id_token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
