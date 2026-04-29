package com.savrti.plantexchange.dto;

public class AuthResponse {

    private String token;
    private UserResponseDTO user;

    public AuthResponse(String token, UserResponseDTO user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() { return token; }
    public UserResponseDTO getUser() { return user; }
}
