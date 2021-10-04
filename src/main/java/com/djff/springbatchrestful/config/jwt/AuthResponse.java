package com.djff.springbatchrestful.config.jwt;

import java.util.Date;

public class AuthResponse {
    final private String token;
    final private Date expiresAt;


    public AuthResponse(String token, Date expiresAt) {
        this.token = token;
        this.expiresAt = expiresAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public String getToken() {
        return token;
    }
}
