package pl.edu.agh.student.mprezes.lvoteandroid.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by majew on 25.05.2017.
 */

public class AuthenticationResponseDTO {

    @JsonProperty("id_token")
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
