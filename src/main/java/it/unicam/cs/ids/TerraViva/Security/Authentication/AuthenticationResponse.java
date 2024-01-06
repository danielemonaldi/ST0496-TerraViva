package it.unicam.cs.ids.TerraViva.Security.Authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    @JsonProperty("access_token")
    private final String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }
}